import com.intellij.codeInsight.completion.*;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import com.jetbrains.twig.elements.TwigElementTypes;
import fr.adrienbrault.idea.symfony2plugin.Symfony2ProjectComponent;
import fr.adrienbrault.idea.symfony2plugin.TwigHelper;
import org.jetbrains.annotations.NotNull;

public class TwigTemplateCompletionContributor extends CompletionContributor {

    public TwigTemplateCompletionContributor() {

        extend(CompletionType.BASIC, PlatformPatterns.or(
            TwigHelper.getTemplateFileReferenceTagPattern(),
            TwigHelper.getTagTernaryPattern(TwigElementTypes.EXTENDS_TAG)
        ), new TemplateCompletionProvider());

        // all file template "include" pattern
        extend(CompletionType.BASIC, PlatformPatterns.or(
            TwigHelper.getPrintBlockFunctionPattern("include", "source"),
            TwigHelper.getIncludeTagArrayPattern(),
            TwigHelper.getTagTernaryPattern(TwigElementTypes.INCLUDE_TAG)
        ),  new TemplateCompletionProvider());
    }

    private class TemplateCompletionProvider extends CompletionProvider<CompletionParameters> {
        public void addCompletions(@NotNull CompletionParameters parameters,
            ProcessingContext context,
            @NotNull CompletionResultSet resultSet) {

            if(!Symfony2ProjectComponent.isEnabled(parameters.getPosition())) {
                return;
            }

            resultSet.addAllElements(TwigHelper.getTwigLookupElements(parameters.getPosition().getProject()));
        }
    }
}
