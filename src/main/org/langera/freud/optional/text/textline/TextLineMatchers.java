package org.langera.freud.optional.text.textline;

import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.core.matcher.IntOperatorMatcherAdapter;
import org.langera.freud.core.matcher.IntOperatorMatcherBuilder;
import org.langera.freud.core.matcher.RegexMatcher;
import org.langera.freud.core.matcher.RegexMatcherAdapter;

public final class TextLineMatchers
{
    private TextLineMatchers()
    {
        // static utility
    }

    public static FreudMatcher<TextLine> lineMatches(final String regex)
    {
        return regexMatcher(regex, true);
    }


    public static FreudMatcher<TextLine> lineContains(final String regex)
    {
        return regexMatcher(regex, false);
    }

    public static IntOperatorMatcherBuilder<TextLine> lineLength()
    {
        return new IntOperatorMatcherBuilder<TextLine>(new IntOperatorMatcherAdapter<TextLine>()
        {
            @Override
            public int valueOf(final TextLine matched)
            {
                return matched.getLine().length();
            }

            @Override
            public String matcherDisplayName()
            {
                return "LineLength()";
            }
        });
    }

    public static IntOperatorMatcherBuilder<TextLine> lineNumber()
    {
        return new IntOperatorMatcherBuilder<TextLine>(new IntOperatorMatcherAdapter<TextLine>()
        {
            @Override
            public int valueOf(final TextLine matched)
            {
                return matched.getLineNumber();
            }

            @Override
            public String matcherDisplayName()
            {
                return "LineNumber()";
            }
        });
    }

    private static FreudMatcher<TextLine> regexMatcher(final String regex, final boolean completeMatch)
    {
        return new RegexMatcher<TextLine>(regex, completeMatch, new RegexMatcherAdapter<TextLine>()
        {
            @Override
            public String getStringToMatch(final TextLine toBeAnalysed)
            {
                return toBeAnalysed.getLine();
            }

            @Override
            public String matcherDisplayName()
            {
                return "TextLine";
            }
        });
    }
}