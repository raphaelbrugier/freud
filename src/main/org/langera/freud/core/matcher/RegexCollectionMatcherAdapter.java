package org.langera.freud.core.matcher;

import java.util.List;

public interface RegexCollectionMatcherAdapter<T> {

    List<String> getStringsToMatch(T toBeAnalysed);

    String matcherDisplayName();
}

