package org.langera.freud.core.matcher;

import java.util.List;
import java.util.regex.Pattern;

import org.hamcrest.Description;

public class StringCollectionMatcherBuilder<T>
{
    private final RegexCollectionMatcherAdapter<T> adapter;

    public StringCollectionMatcherBuilder(final RegexCollectionMatcherAdapter<T> adapter)
    {
        this.adapter = adapter;
    }

    public FreudMatcher<T> matches(final String regex)
    {
        return new RegexMatcher<T>(regex, true, adapter);
    }

    public FreudMatcher<T> contains(final String regex)
    {
        return new RegexMatcher<T>(regex, false, adapter);
    }

    public FreudMatcher<T> matches(final String regex, final int regexFlags)
    {
        return new RegexMatcher<T>(regex, true, regexFlags, adapter);
    }

    public FreudMatcher<T> contains(final String regex, final int regexFlags)
    {
        return new RegexMatcher<T>(regex, false, regexFlags, adapter);
    }



    private static final class RegexMatcher<T> extends FreudMatcher<T>
    {
        private final Pattern regex;
        private final boolean completeMatch;
        private final RegexCollectionMatcherAdapter<T> adapter;

        public RegexMatcher(final String regex, final boolean completeMatch,
                            final RegexCollectionMatcherAdapter<T> adapter)
        {
            this(regex, completeMatch, 0, adapter);
        }

        public RegexMatcher(final String regex, final boolean completeMatch,
                            final int regexFlags,
                            final RegexCollectionMatcherAdapter<T> adapter)
        {
            this.adapter = adapter;
            this.completeMatch = completeMatch;
            this.regex = Pattern.compile(regex, regexFlags);
        }

        public final boolean matchesSafely(final T toBeAnalysed)
        {
        	boolean ok  =false;
            final List<String> stringsToMatch = adapter.getStringsToMatch(toBeAnalysed);
            for (String stringToMatch: stringsToMatch) {
            	
            	boolean matching = (completeMatch) ? regex.matcher(stringToMatch).matches() :
            		regex.matcher(stringToMatch).find();
            	if(matching) {
            		ok = true;
            	}
			} 
            
            return ok;
        }

        @Override
        public String toString()
        {
            return adapter.matcherDisplayName() + ((completeMatch) ? "Match" : "Contains") +
                    "(" + regex.pattern() + ")";
        }

        public void describeTo(Description description)
        {
            description.appendText(toString());
        }
    }
}