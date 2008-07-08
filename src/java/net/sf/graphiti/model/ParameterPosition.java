package net.sf.graphiti.model;

import net.sf.graphiti.ontology.enums.Position;

public enum ParameterPosition {
	Center, East, North, NorthEast, NorthWest, South, SouthEast, SouthWest, West;

	public static ParameterPosition createFromOntPosition(Position pos) {
		return createFromOntString(pos.toString());
	}

	public static ParameterPosition createFromOntString(
			String stringRepresentation) {
		if (stringRepresentation.equals("North")) {
			return ParameterPosition.North;
		} else if (stringRepresentation.equals("South")) {
			return ParameterPosition.South;
		} else if (stringRepresentation.equals("East")) {
			return ParameterPosition.East;
		} else if (stringRepresentation.equals("West")) {
			return ParameterPosition.West;
		} else if (stringRepresentation.equals("Center")) {
			return ParameterPosition.Center;
		} else if (stringRepresentation.equals("NorthEast")) {
			return ParameterPosition.NorthEast;
		} else if (stringRepresentation.equals("NorthWest")) {
			return ParameterPosition.NorthWest;
		} else if (stringRepresentation.equals("SouthEast")) {
			return ParameterPosition.SouthEast;
		} else if (stringRepresentation.equals("SouthWest")) {
			return ParameterPosition.SouthWest;
		} else {
			return ParameterPosition.Center;
		}
	}
}
