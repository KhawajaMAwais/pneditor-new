package net.matmas.pnsynthesizer.layout.graphviz;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.Node;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.xml.IdFromObject;

/**
 *
 * @author matmas
 */
public class GraphvizLayout {
	
	private PetriNet subnet;
	
	public GraphvizLayout(PetriNet subnet) {
		this.subnet = subnet;
	}
	
	public void layout() throws IOException {
		Process process = executeProcess();
		writeGraph(process.getOutputStream());
		readGraphLayout(process.getInputStream());
	}

	private Process executeProcess() throws IOException {
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("./dot.exe"); //Windows
			process.getErrorStream().close();
		} catch (IOException ex) {
			File dot = new File("./dot");
			if ( !dot.canExecute()) {
				dot.setExecutable(true);
			}
			process = Runtime.getRuntime().exec("./dot"); //Unix-like OS
		}
		return process;
	}

	private void writeGraph(OutputStream outputStream) {
		PrintStream out = new PrintStream(outputStream);
		out.println("digraph net {");
		out.println("graph [rankdir=LR, splines=polyline]");
		String nodeWidth = "0.6";
		out.println("node [shape=box, fixedsize=true, width=" + nodeWidth + ", height=" + nodeWidth + "]");

		List<Node> places = new ArrayList<Node>(subnet.getPlaces());
		Collections.shuffle(places);
		List<Node> transitions = new ArrayList<Node>(subnet.getTransitions());
		Collections.shuffle(transitions);
//		Collections.sort(transitions);
//		Collections.reverse(transitions);
		for (Node transition : transitions) {
			out.println(getNodeId(transition));
		}
		out.println("node [shape=circle, fixedsize=true, width=" + nodeWidth + "]");
		for (Node place : places) {
			out.println(getNodeId(place));
		}
		for (Arc arc : subnet.getArcs()) {
			out.println(getNodeId(arc.getSource()) + "->" + getNodeId(arc.getDestination()));
		}
		out.println("}");
		out.close();
	}

	private void readGraphLayout(InputStream inputStream) {
		clearAllBreakPoints();
		Scanner lineScanner = new Scanner(inputStream);
		lineScanner.nextLine();
		lineScanner.nextLine();
		lineScanner.nextLine();
		lineScanner.nextLine();
		String line;
		while (lineScanner.hasNextLine()) {
			line = lineScanner.nextLine();
			while (line.endsWith("\\")) {
				line = line.substring(0, line.length() - 1);
				line = line + lineScanner.nextLine();
			}
			if (!line.equals("}")) {
				if (line.contains("height")) { // Node
					String id = getId(line);
					String pos = getPos(line);
					int x = getX(pos);
					int y = getY(pos);
					Node node = getNode(id);
					node.setCenter(new net.matmas.util.Point(x, y));
				}
				else { // Arc
					String bothId = getId(line);
					Scanner idScanner = new Scanner(bothId);
					idScanner.useDelimiter("->");
					String idFrom = idScanner.next().trim();
					String idTo = idScanner.next().trim();
					Arc arc = getNode(idFrom).getConnectedArcToNode(getNode(idTo));
					String multiplePos = getPos(line);
					Scanner posScanner = new Scanner(multiplePos);
					posScanner.useDelimiter(" ");
					while (posScanner.hasNext()) {
						String singlePos = posScanner.next();
						if (!singlePos.startsWith("e,")) {
							int x = getX(singlePos);
							int y = getY(singlePos);
							arc.addDistantBreakPointToEnd(new net.matmas.util.Point(x, y));
							arc.cleanupUnecessaryBreakPoints();
						}
					}
				}
			}
		}
		translateToCenter();
	}

	private Node getNode(String id) {
		for (Node node : subnet.getNodes()) {
			if (matchesNodeId(node, id)) {
				return node;
			}
		}
		return null;
	}

	private String getId(String line) {
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter("\\[");
		String id = scanner.next().trim();
		return id;
	}

	private String getPos(String line) {
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter("\\[");
		String id = scanner.next().trim();
		scanner.useDelimiter("pos=\"");
		scanner.next();
		scanner.useDelimiter("\"");
		scanner.next();
		String pos = scanner.next();
		return pos;
	}

	private int getX(String pos) {
		Scanner posScanner = new Scanner(pos);
		posScanner.useDelimiter(",");
		int x = posScanner.nextInt();
		return x;
	}

	private int getY(String pos) {
		Scanner posScanner = new Scanner(pos);
		posScanner.useDelimiter(",");
		posScanner.nextInt();
		int y = posScanner.nextInt();
		return y;
	}

	private void clearAllBreakPoints() {
		for (Arc arc : subnet.getArcs()) {
			arc.removeBreakPoints();
		}
	}

	private void translateToCenter() {
		Rectangle bounds = subnet.getBounds();
		Point translationToCenter = new Point();
		translationToCenter.translate(Math.round((float)bounds.getCenterX()), Math.round((float)bounds.getCenterX()));
//                translationToCenter.translate(Math.round(-(float)bounds.getCenterX()), Math.round(-(float)bounds.getCenterY()));
		for (Element element : subnet.getElements()) {
			element.moveBy(translationToCenter.x, translationToCenter.y);
		}
	}

	private IdFromObject idFromObject = new IdFromObject();

	private String getNodeId(Node node) {
		return idFromObject.getId(node);
	}

	private boolean matchesNodeId(Node node, String id) {
		return id.equals(idFromObject.getId(node));
	}
}
