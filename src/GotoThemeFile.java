import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class GotoThemeFile extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Project project = e.getData(PlatformDataKeys.PROJECT);

        String txt= Messages.showInputDialog(project, "Theme to use?", "Theme package name", Messages.getQuestionIcon());
        Messages.showMessageDialog(project, "TODO: navigate to the file in theme " + txt + ".", "Navigate", Messages.getInformationIcon());
    }
}
