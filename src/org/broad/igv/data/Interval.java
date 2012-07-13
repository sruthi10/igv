/*
 * Copyright (c) 2007-2012 The Broad Institute, Inc.
 * SOFTWARE COPYRIGHT NOTICE
 * This software and its documentation are the copyright of the Broad Institute, Inc. All rights are reserved.
 *
 * This software is supplied without any warranty or guaranteed support whatsoever. The Broad Institute is not responsible for its use, misuse, or functionality.
 *
 * This software is licensed under the terms of the GNU Lesser General Public License (LGPL),
 * Version 2.1 which is available at http://www.opensource.org/licenses/lgpl-2.1.php.
 */

package org.broad.igv.data;

import org.broad.tribble.Feature;


/**
 * Interface used to represent data which spans an interval,
 * including the zoom level. If data is different for different
 * zoom levels, merging may not be possible
 *
 * User: jacob
 * Date: 2012-Jul-05
 */
public interface Interval extends Feature {

    /**
     * Determine whether this interval fully contains the specified
     * input interval
     * @param chr
     * @param start
     * @param end
     * @param zoom
     * @return
     */
    public boolean contains(String chr, int start, int end, int zoom);

    /**
     * Determine whether this interval overlaps the specified
     * interval at all
     * @param chr
     * @param start
     * @param end
     * @param zoom
     * @return
     */
    public boolean overlaps(String chr, int start, int end, int zoom);


    /**
     * Merge another interval with this one
     * @param i
     * @return True if the interval was merged, false
     * if not. Cannot necessarily merge an interval if there
     * is no overlap; if the Interval contains data
     * that data would be missing
     */
    public boolean merge(Interval i);

    public int getZoom();
}