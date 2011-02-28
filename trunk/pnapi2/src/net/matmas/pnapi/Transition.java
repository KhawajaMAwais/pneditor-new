package net.matmas.pnapi;

import java.awt.Color;
import java.awt.Graphics;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class Transition extends Node implements Cloneable {

	@Override
	public void draw(Graphics g, DrawingOptions drawingOptions) {
		Color color = drawingOptions.getElementColors().get(this);
		if (color == null) {
			color = Color.black;
		}
		final Color lightGreen = new Color(227, 244, 215);
		g.setColor(lightGreen);
		g.fillRect(getStart().getX(), getStart().getY(), getWidth(), getHeight());
		g.setColor(color);
		g.drawRect(getStart().getX(), getStart().getY(), getWidth()-1, getHeight()-1);
		getLabel().draw(g, drawingOptions);
	}

	@Override
	public Transition getClone() {
		return (Transition)super.getClone();
	}

	public boolean canBeMovedFrom(Point point) {
		return containsPoint(point);
	}
        // -----------------------------------------------Workflow anal...

	private Transition ANDJoin;

	public Transition getANDJoin() {
		return ANDJoin;
	}

	public void setANDJoin(Transition ANDJoin) {
		this.ANDJoin = ANDJoin;
	}

        // -----------------------------------------------Workflow anal...

	private Boolean MMc=false;

	public Boolean getMMc() {
		return MMc;
	}

	public void setMMc(Boolean MMc) {
		this.MMc = MMc;
	}


        // -----------------------------------------------Workflow anal...

	private Boolean MM1=false;

	public Boolean getMM1() {
		return MM1;
	}

	public void setMM1(Boolean MM1) {
		this.MM1 = MM1;
	}


        // -----------------------------------------------Workflow anal...

	private Boolean IsANDSplit=false;

	public Boolean getIsANDSplit() {
		return IsANDSplit;
	}

	public void setIsANDSplit(Boolean IsANDSplit) {
		this.IsANDSplit = IsANDSplit;
	}


        // -----------------------------------------------Workflow anal...

	private double ServisRate=0;

	public double getServisRate() {
		return ServisRate;
	}

	public void setServisRate(double ServisRate) {
		this.ServisRate = ServisRate;
	}

         // -----------------------------------------------Workflow anal...

	private double SystemTime=0;

	public double getSystemTime() {
		return SystemTime;
	}

	public void SetSystemTime(double SystemTime) {
		this.SystemTime = SystemTime;
	}

        // -----------------------------------------------Workflow anal...

	private double WaitingTime=0;

	public double getWaitingTime() {
		return WaitingTime;
	}

	public void SetWaitingTime(double WaitingTime) {
		this.WaitingTime = WaitingTime;
	}

         // -----------------------------------------------Workflow anal...

	private double Utilization=0;

	public double getUtilization() {
		return Utilization;
	}

	public void SetUtilization(double Utilization) {
		this.Utilization = Utilization;
	}

         // -----------------------------------------------Workflow anal...

	private double Probability=1;

	public double getProbability() {
		return Probability;
	}

	public void SetProbability(double Probability) {
		this.Probability = Probability;
	}

        // -----------------------------------------------Workflow anal...

	private Integer NumberOfServers=1;

	public Integer getNumberOfServers() {
		return NumberOfServers;
	}

	public void setNumberOfServers(Integer NumberOfServers) {
		this.NumberOfServers = NumberOfServers;
	}

         // -----------------------------------------------Workflow anal...

	private String ANDJoinId;

	public String getANDJoinId() {
		return ANDJoinId;
	}

	public void setANDJoinId(String ANDJoinId) {
		this.ANDJoinId = ANDJoinId;
	}
        
}
