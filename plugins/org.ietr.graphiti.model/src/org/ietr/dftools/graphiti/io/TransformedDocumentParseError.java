/**
 * Copyright or © or Copr. IETR/INSA - Rennes (2008 - 2019) :
 *
 * Antoine Morvan [antoine.morvan@insa-rennes.fr] (2017 - 2019)
 * Clément Guy [clement.guy@insa-rennes.fr] (2014)
 * Matthieu Wipliez [matthieu.wipliez@insa-rennes.fr] (2008 - 2010)
 *
 * This software is a computer program whose purpose is to help prototyping
 * parallel applications using dataflow formalism.
 *
 * This software is governed by the CeCILL  license under French law and
 * abiding by the rules of distribution of free software.  You can  use,
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability.
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or
 * data to be ensured and,  more generally, to use and operate it in the
 * same conditions as regards security.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */
package org.ietr.dftools.graphiti.io;

/**
 * This class provides an exception that is launched when the transformed document (after zero or one grammar
 * transformation and zero or more XSLT transformations) cannot be parsed.
 *
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 *
 */
public class TransformedDocumentParseError extends Exception {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -2161343938175674853L;

  /**
   * Constructs a new exception with the specified detail message. The cause is not initialized, and may subsequently be
   * initialized by a call to {@link #initCause}.
   *
   * @param message
   *          the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
   */
  public TransformedDocumentParseError(final String message) {
    super(message);
  }

  /**
   * Constructs a new exception with the specified detail message and cause.
   * <p>
   * Note that the detail message associated with <code>cause</code> is <i>not</i> automatically incorporated in this
   * exception's detail message.
   *
   * @param message
   *          the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
   * @param cause
   *          the cause (which is saved for later retrieval by the {@link #getCause()} method). (A <tt>null</tt> value
   *          is permitted, and indicates that the cause is nonexistent or unknown.)
   */
  public TransformedDocumentParseError(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new exception with the specified cause and a detail message of
   * <tt>(cause==null ? null : cause.toString())</tt> (which typically contains the class and detail message of
   * <tt>cause</tt>). This constructor is useful for exceptions that are little more than wrappers for other throwables
   * (for example, {@link java.security.PrivilegedActionException}).
   *
   * @param cause
   *          the cause (which is saved for later retrieval by the {@link #getCause()} method). (A <tt>null</tt> value
   *          is permitted, and indicates that the cause is nonexistent or unknown.)
   */
  public TransformedDocumentParseError(final Throwable cause) {
    super(cause);
  }
}
