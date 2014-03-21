package org.openstack4j.model.network;

import org.codehaus.jackson.annotate.JsonCreator;

/**
 * The state of a Network (Neutron) entity
 * 
 * @author Jeremy Unruh
 */
public enum State {
  ACTIVE,
  DOWN,
  BUILD,
  ERROR,
  UNRECOGNIZED;
  
	@JsonCreator
	public static State forValue(String value) {
		if (value != null)
		{
			for (State s : State.values()) {
				if (s.name().equalsIgnoreCase(value))
					return s;
			}
		}
		return State.UNRECOGNIZED;
	}
}
