package net.sf.graphiti.ui.actions;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.gef.ui.actions.Clipboard;

/**
 * @author stage02
 */
public class PreesmClipboard extends Clipboard {

	protected static PreesmClipboard _instance = new PreesmClipboard();

	/**
	 * The event name used for {@link PreesmClipboard#fireContentsSet()}
	 */
	public static final String CONTENTS_SET_EVENT = "ContentsSet";

	/**
	 * Get the default Clipboard
	 * 
	 * @return - The default Clipboard
	 */
	public static Clipboard getDefault() {
		return _instance;
	}

	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	/**
	 * Do not allow direct instantiation of a Clipboard
	 */
	@SuppressWarnings("deprecation")
	private PreesmClipboard() {
		super();
	}

	/**
	 * Add a {@link PropertyChangeListener} to this Clipboard
	 * 
	 * @param l
	 */
	public void addPropertyChangeListener(PropertyChangeListener l) {
		listeners.addPropertyChangeListener(l);
	}

	/**
	 * Fires a {@link PropertyChangeEvent} anytime the contents of the
	 * <code>Clipboard</code> are set.
	 * 
	 */
	protected void fireContentsSet() {
		PropertyChangeEvent event = new PropertyChangeEvent(this,
				CONTENTS_SET_EVENT, null, getContents());
		listeners.firePropertyChange(event);
	}

	/**
	 * Remove a {@link PropertyChangeListener} to this Clipboard
	 * 
	 * @param l
	 */
	public void removePropertyChangeListener(PropertyChangeListener l) {
		listeners.removePropertyChangeListener(l);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.actions.Clipboard#setContents(java.lang.Object)
	 */
	@Override
	public void setContents(Object contents) {
		super.setContents(contents);
		fireContentsSet();
	}
}
