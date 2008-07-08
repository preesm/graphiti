package net.sf.graphiti.ui.figure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.graphiti.model.ParameterPosition;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.geometry.Point;

public class PropertyLocator extends ConnectionLocator {
	static HashMap<Connection,List<PropertyLocator>> positions = new HashMap<Connection,List<PropertyLocator>>();
	private ParameterPosition pos;

	public PropertyLocator(Connection c, ParameterPosition p) {
		super(c);
		if(positions.get(c) == null){
			List<PropertyLocator> list = new ArrayList<PropertyLocator>() ;
			positions.put(c, list);
		}
		pos = p;
		positions.get(c).add(this);
	}

	protected ParameterPosition getPosition() {
		return pos;
	}

	protected Point getReferencePoint() {
		Connection conn = getConnection();
		List<PropertyLocator> listOfProperties = positions.get(conn);
		int maxIndex = listOfProperties.indexOf(this);
		int dec = 5 ;
		for(int i = 0 ; i < maxIndex ; i ++){
			if(listOfProperties.get(i).pos ==  this.pos){
				dec += 10 ;
			}
		}
		int xdirec = 0;
		int ydirec = 0;
		Point p = Point.SINGLETON;
		Point f = conn.getPoints().getFirstPoint();
		Point l = conn.getPoints().getLastPoint();

		if (l.x > f.x) {
			xdirec = 1;
		} else {
			xdirec = -1;
		}

		if (l.y > f.y) {
			ydirec = 1;
		} else {
			ydirec = -1;
		}
		if (pos.equals(ParameterPosition.West)
				|| pos.equals(ParameterPosition.NorthWest)
				|| pos.equals(ParameterPosition.SouthWest)) {
			Point refP = conn.getPoints().getFirstPoint().getCopy();
			conn.getParent().translateToAbsolute(refP);
			p.setLocation(refP.x + (dec * xdirec), refP.y + (dec * ydirec));
		} else if (pos.equals(ParameterPosition.East)
				|| pos.equals(ParameterPosition.NorthEast)
				|| pos.equals(ParameterPosition.SouthEast)) {
			Point refP = conn.getPoints().getLastPoint().getCopy();
			conn.getParent().translateToAbsolute(refP);
			p.setLocation(refP.x - (dec * xdirec), refP.y - (dec * ydirec));
		} else {
			Point refP = conn.getPoints().getMidpoint().getCopy();
			conn.getParent().translateToAbsolute(refP);
			p.setLocation(refP.x - (dec * xdirec), refP.y - (dec * ydirec));
		}
		return p;
	}

}
