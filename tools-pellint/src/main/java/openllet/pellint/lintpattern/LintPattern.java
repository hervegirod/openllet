// Copyright (c) 2006 - 2008, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// This source code is available under the terms of the Affero General Public License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

package openllet.pellint.lintpattern;

import openllet.pellint.format.LintFormat;
import openllet.pellint.model.LintFixer;

/**
 * <p>
 * Title: Lint Pattern Interface
 * </p>
 * <p>
 * Description: The root interface for all lint patterns which provides some basic presentation interfaces.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: Clark & Parsia, LLC. <http://www.clarkparsia.com>
 * </p>
 *
 * @author Harris Lin
 */
public interface LintPattern
{

	/**
	 * @return The name of the pattern.
	 */
	String getName();

	/**
	 * @return The detailed description of the pattern.
	 */
	String getDescription();

	/**
	 * Returns <code>true</code> if this pattern provides an automatic fix for every {@link openllet.pellint.model.Lint} it matches. An automatic fix for
	 * every {@link openllet.pellint.model.Lint} can be made available by calling {@link openllet.pellint.model.Lint#setLintFixer(LintFixer)}.
	 *
	 * @return <code>true</code> if this pattern provides an automatic fix for every {@link openllet.pellint.model.Lint} it matches.
	 * @see    openllet.pellint.model.Lint#setLintFixer(LintFixer)
	 */
	boolean isFixable();

	/**
	 * @return The default {@link LintFormat} for this pattern.
	 * @see    LintFormat
	 */
	LintFormat getDefaultLintFormat();
}
