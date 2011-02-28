package net.matmas.pnapi.xml;

import net.matmas.pnapi.Marking;
import net.matmas.pnapi.Place;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author matmas
 */
@Root(name="place")
public class XmlPlace extends XmlNode {

	@Element
	public int tokens;

        @Element
	public Boolean IsIterationStart;

        @Element
	public Boolean IsORSplit;

        @Element
	public Boolean IsStartPlace;

        @Element
	public Boolean IsEndPlace;

        @Element
	public double ArrivalRate;

        @Element
	public String ORJoinId;

        @Element
	public String IterJoinId;

	public XmlPlace() {
	}

	public XmlPlace(Place place, Marking marking) {
		super(place);
		this.tokens = marking.getTokens(place);
                IsIterationStart = place.getIsIterationStart();
                IsORSplit = place.getIsORSplit();
                IsStartPlace = place.getIsStartPlace();
                IsEndPlace = place.getIsEndPlace();
                ArrivalRate = place.getArrivalRate();
                if (IsORSplit)
                {
                    ORJoinId = place.getORJoin().getId();
                }
                else {ORJoinId ="null";}
                if (IsIterationStart)
                {
                    IterJoinId = place.getIterationEnd().getId();
                }
                else {IterJoinId ="null";}
	}

}
