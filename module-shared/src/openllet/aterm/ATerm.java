/*
 * Copyright (c) 2002-2007, CWI and INRIA
 *
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * * Neither the name of the University of California, Berkeley nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package openllet.aterm;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import openllet.shared.hash.HashFunctions;

/**
 * This is the base interface for all ATerm interfaces,
 * which will ultimately be implemented by two separate
 * ATerm Factories (a native and a pure one).
 *
 * @author Hayco de Jong (jong@cwi.nl)
 * @author Pieter Olivier (olivierp@cwi.nl)
 */
public interface ATerm extends Visitable, Identifiable
{
	int	GOLDEN_RATIO	= HashFunctions.GOLDEN_RATIO;

	/**
	 * A term of type INT
	 */
	int	INT				= 2;

	/**
	 * A term of type REAL
	 */
	int	REAL			= 3;

	/**
	 * A term of type APPL (function application)
	 */
	int	APPL			= 1;

	/**
	 * A term of type LIST
	 */
	int	LIST			= 4;

	/**
	 * A term of type PLACEHOLDER
	 */
	int	PLACEHOLDER		= 5;

	/**
	 * A term of type BLOB (Binary Large OBject)
	 */
	int	BLOB			= 6;

	/**
	 * A term of type AFUN (function symbol)
	 */
	int	AFUN			= 7;

	/**
	 * A term of type LONG
	 */
	int	LONG			= 8;

	/**
	 * Gets the type of this term.
	 *
	 *
	 * @return the type of this ATerm.
	 *
	 * @see    #INT
	 * @see    #REAL
	 * @see    #APPL
	 * @see    #LIST
	 * @see    #PLACEHOLDER
	 * @see    #BLOB
	 * @see    #AFUN
	 * @see    #LONG
	 */
	int getType();

	/**
	 * Gets a hashcode value of this term.
	 *
	 * @return the hashcode of this term.
	 *
	 */
	@Override
	int hashCode();

	/**
	 * Matches this term against a String pattern. The pattern is
	 * parsed into a term, which this term is then matched against.
	 *
	 * @param  pattern    the string pattern to match this term against.
	 *
	 * @return            a list containing the subterms matching the placeholders
	 *                    if the match succeeds, or null if the match fails.
	 *
	 * @throws ParseError if pattern cannot be parsed into a term.
	 *
	 * @see               #match(ATerm)
	 */
	List<Object> match(final String pattern);

	/**
	 * Matches this term against a term pattern. A list containing
	 * the subterms matching the placeholders in the pattern is
	 * built as this term is matched against the pattern.
	 *
	 * @param  pattern The term pattern to match this term against.
	 *
	 * @return         a list containing the subterms matching the placeholders
	 *                 if the match succeeds, or null if the match fails.
	 *
	 */
	List<Object> match(final ATerm pattern);

	/**
	 * Checks equality of this term against another term.
	 * This method exists to keep a tight relation to the C-library.
	 * Experienced Java programmers might feel more comfortable using
	 * the {@link #equals} method.
	 *
	 *
	 * @param  term the term to check for equality.
	 *
	 * @return      true iff terms are equal (including
	 *              any annotations they might have!), false otherwise.
	 *
	 * @see         #equals(Object)
	 *
	 */
	boolean isEqual(final ATerm term);

	/**
	 * Checks equality of this term against any java object.
	 * Note that for two terms to be equal, any annotations they
	 * might have must be equal as well.
	 *
	 * @param  obj the object to check for equality.
	 *
	 * @return     true iff term equals obj (including annotations).
	 *
	 * @see        #isEqual
	 */
	@Override
	boolean equals(final Object obj);

	/**
	 * Write a term to a text file/stream.
	 *
	 * @param  stream      the stream to write to
	 * @throws IOException ex
	 */
	void writeToTextFile(final OutputStream stream) throws IOException;

	/**
	 * Write a term to a openllet.shared.hash text file/stream.
	 * An efficient openllet.shared.hash ASCII representation of this term is written to
	 * the stream.
	 *
	 * @param  stream      the stream to write this term to
	 * @throws IOException ex
	 */
	void writeToSharedTextFile(final OutputStream stream) throws IOException;

	/**
	 * Create a new term based on this term as a pattern and a list of arguments.
	 *
	 * @param  args the list of arguments used to fill up holes in the pattern
	 *
	 * @return      the constructed openllet.aterm
	 */
	ATerm make(final List<Object> args);

	/**
	 * Retrieves the factory responsible for creating this ATerm.
	 *
	 * @return the factory that created this ATerm object.
	 */

	ATermFactory getFactory();

	/**
	 * Gets a string representation of this term.
	 *
	 *
	 * @return a string representation of this term.
	 *
	 */
	@Override
	String toString();
}
