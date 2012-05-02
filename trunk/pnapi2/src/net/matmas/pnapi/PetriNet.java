package net.matmas.pnapi;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import net.matmas.pnapi.properties.WithProperties;
import net.matmas.pnapi.properties.Properties;
import net.matmas.pnapi.properties.Property;
import net.matmas.pnapi.properties.StringProperty;
import net.matmas.util.CachedGraphics2D;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class PetriNet implements Iterable<Element>, WithProperties {

    private Marking marking = new Marking(this);

    public Marking getMarking() {
        return marking;
    }

    public void setMarking(Marking marking) {
        this.marking = marking;
    }
    // -------------------------------------------------------------------------
    private List<Place> places = new LinkedList<Place>();

    public List<Place> getPlaces() {
        return Collections.unmodifiableList(places);
    }

    public void addPlace(Place place) {
        places.add(place);
        place.setPetriNet(this);
    }

    public void addPlace(Place place, String id) {
        places.add(place);
        place.setPetriNet(this, id);
    }

    public void removePlace(Place place) {
        places.remove(place);
    }
    // -------------------------------------------------------------------------
    private List<Transition> transitions = new LinkedList<Transition>();

    public List<Transition> getTransitions() {
        return Collections.unmodifiableList(transitions);
    }

    public void addTransition(Transition transition) {
        transitions.add(transition);
        transition.setPetriNet(this);
    }

    public void addTransition(Transition transition, String id) {
        transitions.add(transition);
        transition.setPetriNet(this, id);
    }

    public void removeTransition(Transition transition) {
        transitions.remove(transition);
    }
    // -------------------------------------------------------------------------
    private List<Arc> arcs = new LinkedList<Arc>();

    public List<Arc> getArcs() {
        return Collections.unmodifiableList(arcs);
    }

    public void addArc(Arc arc) {
        arcs.add(arc);
        arc.setPetriNet(this);
    }

    public void removeArc(Arc arc) {
        arcs.remove(arc);
    }
    // -------------------------------------------------------------------------
    private List<Subnet> subnets = new ArrayList<Subnet>();

    public void addSubnet(Subnet subnet) {
        subnets.add(subnet);
        subnet.setPetriNet(this);
    }

    public void removeSubnet(Subnet subnet) {
        subnets.remove(subnet);
    }

    // -------------------------------------------------------------------------
    public void add(Element element) {
        if (element instanceof Place) {
            this.addPlace((Place) element);
        } else if (element instanceof Transition) {
            this.addTransition((Transition) element);
        } else if (element instanceof Arc) {
            this.addArc((Arc) element);
        } else if (element instanceof Subnet) {
            this.addSubnet((Subnet) element);
        } else {
            assert false;
        }
    }

    public void remove(Element element) {
        if (element instanceof Place) {
            this.removePlace((Place) element);
        } else if (element instanceof Transition) {
            this.removeTransition((Transition) element);
        } else if (element instanceof Arc) {
            this.removeArc((Arc) element);
        } else if (element instanceof Subnet) {
            this.removeSubnet((Subnet) element);
        } else {
            assert false;
        }
    }

    // -------------------------------------------------------------------------
    public void addAll(Collection<Element> elements) {
        for (Element element : elements) {
            this.add(element);
        }
    }

    public void removeAll(Collection<Element> elements) {
        for (Element element : elements) {
            this.remove(element);
        }
    }

    // -------------------------------------------------------------------------
    public boolean isEmpty() {
        return places.isEmpty() && transitions.isEmpty() && arcs.isEmpty() && subnets.isEmpty();
    }

    public void clear() {
        this.places.clear();
        this.transitions.clear();
        this.arcs.clear();
        this.subnets.clear();
    }

    // -------------------------------------------------------------------------
    public void draw(Graphics g, DrawingOptions drawingOptions) {
//		((Graphics2D)g).scale(drawingOptions.getZoom(), drawingOptions.getZoom());
        for (Element element : this) {
            element.draw(g, drawingOptions);
        }
//		((Graphics2D)g).scale(1.0 / drawingOptions.getZoom(), 1.0 / drawingOptions.getZoom());
    }

    // -------------------------------------------------------------------------
    public Rectangle getBounds(DrawingOptions drawingOptions) {
        CachedGraphics2D cachedGraphics = new CachedGraphics2D();
        this.draw(cachedGraphics, drawingOptions);
        return cachedGraphics.getIntegerBounds();
    }

    public Rectangle getBounds() {
        DrawingOptions drawingOptions = new DrawingOptions();
        CachedGraphics2D cachedGraphics = new CachedGraphics2D();
        this.draw(cachedGraphics, drawingOptions);
        return cachedGraphics.getIntegerBounds();
    }

    /**
     * Returns an preview image of the net with specified marking.
     * Scale image: image.getScaledInstance(preferredWidth, preferredHeight, Image.SCALE_SMOOTH)
     * Save image: ImageIO.write(image, "png", file);
     */
    public BufferedImage getPreview(Marking marking) {
        CachedGraphics2D cachedGraphics = new CachedGraphics2D();
        cachedGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        DrawingOptions drawingOptions = new DrawingOptions(marking);
        this.draw(cachedGraphics, drawingOptions);
        Rectangle bounds = cachedGraphics.getIntegerBounds();
        int width = bounds.width;
        int height = bounds.height;
        width = Math.max(width, 1);
        height = Math.max(height, 1);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D imageGraphics = (Graphics2D) bufferedImage.getGraphics();
        imageGraphics.fillRect(0, 0, width, height); // paint the background white
        imageGraphics.translate(-bounds.x, -bounds.y);
        cachedGraphics.applyToGraphics(imageGraphics);
        return bufferedImage;
    }

    // -------------------------------------------------------------------------
    public Element getElementByLocation(Point location) {
        for (int i = subnets.size() - 1; i >= 0; i--) {
            Subnet subnet = subnets.get(i);
            if (subnet.containsPoint(location)) {
                return subnet;
            }
        }
        for (int i = transitions.size() - 1; i >= 0; i--) {
            Transition transition = transitions.get(i);
            if (transition.getLabel().containsPoint(location)) {
                return transition.getLabel();
            }
            if (transition.containsPoint(location)) {
                return transition;
            }
        }
        for (int i = places.size() - 1; i >= 0; i--) {
            Place place = places.get(i);
            if (place.getLabel().containsPoint(location)) {
                return place.getLabel();
            }
            if (place.containsPoint(location)) {
                return place;
            }
        }
        for (int i = arcs.size() - 1; i >= 0; i--) {
            Arc arc = arcs.get(i);
            if (arc.containsPoint(location)) {
                return arc;
            }
        }
        return null;
    }
    // -------------------------------------------------------------------------
    private Iterator<Place> placesIterator;
    private Iterator<Transition> transitionsIterator;
    private Iterator<Arc> arcsIterator;
    private Iterator<Subnet> subnetsIterator;

    @SuppressWarnings(value = "unchecked")
    public Iterator<Element> iterator() {
        placesIterator = places.iterator();
        transitionsIterator = transitions.iterator();
        arcsIterator = arcs.iterator();
        subnetsIterator = subnets.iterator();
        return new Iterator() {

            public boolean hasNext() {
                return arcsIterator.hasNext() || placesIterator.hasNext() || transitionsIterator.hasNext() || subnetsIterator.hasNext();
            }

            public Object next() {
                if (arcsIterator.hasNext()) {
                    return arcsIterator.next();
                }
                if (placesIterator.hasNext()) {
                    return placesIterator.next();
                }
                if (transitionsIterator.hasNext()) {
                    return transitionsIterator.next();
                }
                if (subnetsIterator.hasNext()) {
                    return subnetsIterator.next();
                }
                return null;
            }

            public void remove() {
                throw new UnsupportedOperationException("Not supported.");
            }
        };
    }

    // -------------------------------------------------------------------------
    public Set<Node> getNodes() {
        Set<Node> nodes = new HashSet<Node>();
        nodes.addAll(places);
        nodes.addAll(transitions);
        return nodes;
    }

    public Set<Element> getElements() {
        Set<Element> elements = new HashSet<Element>();
        elements.addAll(subnets);
        elements.addAll(arcs);
        elements.addAll(places);
        elements.addAll(transitions);
        return elements;
    }

    public List<Subnet> getSubnets() {
        return subnets;
    }

    // -------------------------------------------------------------------------
    public Arc getArc(Place place, Transition transition, boolean placeToTransition) {
        for (Arc arc : arcs) {
            if (arc.getPlace() == place && arc.getTransition() == transition && arc.isPlaceToTransition() == placeToTransition) {
                return arc;
            }
        }
        return null;
    }
    // -------------------------------------------------------------------------
    private Properties properties = new Properties();

    public Properties getProperties() {
        return this.properties;
    }

    public Transition getTransitionById(String id) {
        for (Transition transition : transitions) {
            if (transition.getId().equals(id)) {
                return transition;
            }
        }
        return null;

    }
    // -------------------------------------------------------------------------
    private transient IdGenerator idGenerator = new IdGenerator();

    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    public String getPetrinetName() {
        String name = null;
        for (Property prop : this.getProperties()) {
            if (prop.getId().equals("name")) {
                StringProperty strprop = (StringProperty) prop;
                name = strprop.getText();
            }
        }
        return name;
    }
    // -------------------------------------------------------------------------
}
