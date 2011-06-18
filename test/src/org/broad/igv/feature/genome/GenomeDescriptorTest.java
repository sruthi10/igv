/*
 * Copyright (c) 2007-2011 by The Broad Institute of MIT and Harvard.  All Rights Reserved.
 *
 * This software is licensed under the terms of the GNU Lesser General Public License (LGPL),
 * Version 2.1 which is available at http://www.opensource.org/licenses/lgpl-2.1.php.
 *
 * THE SOFTWARE IS PROVIDED "AS IS." THE BROAD AND MIT MAKE NO REPRESENTATIONS OR
 * WARRANTES OF ANY KIND CONCERNING THE SOFTWARE, EXPRESS OR IMPLIED, INCLUDING,
 * WITHOUT LIMITATION, WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, NONINFRINGEMENT, OR THE ABSENCE OF LATENT OR OTHER DEFECTS, WHETHER
 * OR NOT DISCOVERABLE.  IN NO EVENT SHALL THE BROAD OR MIT, OR THEIR RESPECTIVE
 * TRUSTEES, DIRECTORS, OFFICERS, EMPLOYEES, AND AFFILIATES BE LIABLE FOR ANY DAMAGES
 * OF ANY KIND, INCLUDING, WITHOUT LIMITATION, INCIDENTAL OR CONSEQUENTIAL DAMAGES,
 * ECONOMIC DAMAGES OR INJURY TO PROPERTY AND LOST PROFITS, REGARDLESS OF WHETHER
 * THE BROAD OR MIT SHALL BE ADVISED, SHALL HAVE OTHER REASON TO KNOW, OR IN FACT
 * SHALL KNOW OF THE POSSIBILITY OF THE FOREGOING.
 */

package org.broad.igv.feature.genome;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jrobinso
 * @date Apr 28, 2011
 */
public class GenomeDescriptorTest {

    /**
     * Test the patch to fix legacy .genome files with incorrect sequence paths
     */

    @Test
    public void testSequencePathFix() {

        // Test for a directory that does exist
        String seqLocation = "/test/data";
        GenomeDescriptor desc = new GenomeZipDescriptor("", 1, false, "", "", "", "", "", seqLocation, null, null,
                false, false);
        assertEquals("test/data", desc.getSequenceLocation());

        // Test for a directory that does not exist
        seqLocation = "/foo/bar";
        desc = new GenomeZipDescriptor("", 1, false, "", "", "", "", "", seqLocation, null, null,
                false, false);
        assertEquals("/foo/bar", desc.getSequenceLocation());
    }
}
