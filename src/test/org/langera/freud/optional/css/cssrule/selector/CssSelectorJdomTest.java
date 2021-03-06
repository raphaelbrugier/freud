/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

package org.langera.freud.optional.css.cssrule.selector;

import org.apache.commons.jxpath.JXPathContext;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class CssSelectorJdomTest
{
    private CssSelectorJdom cssSelectorJdom;

    @Test
    public void testGetSelectorString() throws Exception
    {
        Assert.assertEquals("shouldFail", cssSelectorJdom.getSelectorString());
    }

    @Test
    public void testGetType() throws Exception
    {
        Assert.assertEquals(CssSelector.Type.CLASS, cssSelectorJdom.getType());        
    }

    @Before
    public void setUp() throws Exception
    {
        final SAXBuilder saxBuilder = new SAXBuilder(false);
        Document document = saxBuilder.build(ClassLoader.getSystemResourceAsStream("parsed_css_example.xml"));
        JXPathContext context = JXPathContext.newContext(document.getRootElement());
        cssSelectorJdom = new CssSelectorJdom(null, (Element) context.selectSingleNode("//CLASS"), CssSelector.Combinator.DESCENDANT);

    }
}
