package com.drilon.action;

import com.drilon.InputForm;
import com.drilon.writer.ActivityWriter;
import com.intellij.ide.SaveAndSyncHandler;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFileManager;

public class NewActivityAction extends AnAction {

	@Override
	public void actionPerformed(AnActionEvent anActionEvent) {

		InputForm inputForm = new InputForm();
		inputForm.setPath(anActionEvent.getData(CommonDataKeys.VIRTUAL_FILE).getPath());
		inputForm.setTitle("Create MVP class");
		inputForm.pack();
		inputForm.setModal(true);
		inputForm.setLocationRelativeTo(anActionEvent.getInputEvent().getComponent());
		inputForm.setResizable(true);
		inputForm.initLabels(InputForm.TYPE_ACTIVITY);
		inputForm.setOnOkListener(e -> {
			inputForm.dispose();
			ActivityWriter activityWriter = new ActivityWriter(inputForm.getGeneratedInputModel());
			activityWriter.writeClasses();
			FileDocumentManager.getInstance().saveAllDocuments();
			SaveAndSyncHandler.getInstance().refreshOpenFiles();
			VirtualFileManager.getInstance().refreshWithoutFileWatcher(true);
		});
		inputForm.setVisible(true);
	}
}
