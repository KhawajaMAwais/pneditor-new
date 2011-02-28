package net.matmas.pnapi.xml;

import net.matmas.pnapi.Transition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author matmas
 */
@Root(name="transition")
public class XmlTransition extends XmlNode {

        @Element
	public Boolean MMc;

        @Element
	public Boolean MM1;

        @Element
	public Integer NumberOfServers;

        @Element
	public Boolean IsANDSplit;

        @Element
	public double ServisRate;

        @Element
	public String ANDJoinId;

	public XmlTransition() {
	}

	public XmlTransition(Transition transition) {
		super(transition);
                MMc = transition.getMMc();
                MM1 = transition.getMM1();
                IsANDSplit = transition.getIsANDSplit();
                ServisRate = transition.getServisRate();
                NumberOfServers = transition.getNumberOfServers();
                if (IsANDSplit)
                {
                    ANDJoinId = transition.getANDJoin().getId();
                }
                else {ANDJoinId ="null";}
	}

}
