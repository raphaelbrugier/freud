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

package org.langera.freud.optional.javasource;

import java.util.ArrayList;
import java.util.List;

import org.langera.freud.core.matcher.RegexCollectionMatcherAdapter;
import org.langera.freud.core.matcher.RegexMatcherAdapter;
import org.langera.freud.core.matcher.StringCollectionMatcherBuilder;
import org.langera.freud.core.matcher.StringMatcherBuilder;
import org.langera.freud.optional.javasource.importdecl.ImportDeclaration;


public final class JavaSourceDsl
{
    private JavaSourceDsl()
    {
        // static utility
    }

    public static StringMatcherBuilder<JavaSource> fullClassName()
    {
        return new StringMatcherBuilder<JavaSource>(new RegexMatcherAdapter<JavaSource>()
        {
            @Override
            public String getStringToMatch(final JavaSource toBeAnalysed)
            {
                return toBeAnalysed.getFullClassName();
            }

            @Override
            public String matcherDisplayName()
            {
                return "JavaSourceFullClassName";
            }
        });
    }

    public static StringMatcherBuilder<JavaSource> simpleClassName()
    {
        return new StringMatcherBuilder<JavaSource>(new RegexMatcherAdapter<JavaSource>()
        {
            @Override
            public String getStringToMatch(final JavaSource toBeAnalysed)
            {
                return toBeAnalysed.getSimpleClassName();
            }

            @Override
            public String matcherDisplayName()
            {
                return "JavaSourceSimpleClassName";
            }
        });
    }
    
    public static StringCollectionMatcherBuilder<JavaSource> importDeclarations() 
    {
    	return new StringCollectionMatcherBuilder<JavaSource>(new RegexCollectionMatcherAdapter<JavaSource>() 
    	{
			@Override
			public List<String> getStringsToMatch(JavaSource toBeAnalysed) {
	            {
	            	List<String> importDeclarationsAsString = new ArrayList<String>();
	            	for (ImportDeclaration importDeclaration : toBeAnalysed.getImportDeclarations()) {
	            		importDeclarationsAsString.add(importDeclaration.getImportDeclarationPathAsString());
					}
	            			
	                return importDeclarationsAsString;
	            }
			}
			@Override
			public String matcherDisplayName() 
			{
				return "JavaSourceImportDeclarations";
			}
		});
    }
}
