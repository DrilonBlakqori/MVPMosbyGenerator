package drilon.action;


import com.intellij.ide.SaveAndSyncHandler;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFileManager;

import drilon.InputForm;
import drilon.writer.FragmentWriter;

public class NewFragmentAction extends AnAction {

	@Override
	public void actionPerformed(AnActionEvent anActionEvent) {

		InputForm inputForm = new InputForm();
		inputForm.setPath(anActionEvent.getData(CommonDataKeys.VIRTUAL_FILE).getPath());
		inputForm.setTitle("Create MVP class");
		inputForm.pack();
		inputForm.setModal(true);
		inputForm.setLocationRelativeTo(anActionEvent.getInputEvent().getComponent());
		inputForm.setResizable(true);
		inputForm.initLabels(InputForm.TYPE_FRAGMENT);
		inputForm.setOnOkListener(e -> {
			inputForm.dispose();
			FragmentWriter fragmentWriter = new FragmentWriter(inputForm.getGeneratedInputModel());
			fragmentWriter.writeClasses();
			FileDocumentManager.getInstance().saveAllDocuments();
			SaveAndSyncHandler.getInstance().refreshOpenFiles();
			VirtualFileManager.getInstance().refreshWithoutFileWatcher(true);
		});
		inputForm.setVisible(true);
	}
}
