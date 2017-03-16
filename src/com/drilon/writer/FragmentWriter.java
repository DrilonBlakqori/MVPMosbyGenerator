package com.drilon.writer;

import com.drilon.InputModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FragmentWriter {

	private InputModel inputModel;

	public FragmentWriter(InputModel inputModel) {
		this.inputModel = inputModel;
	}

	public void writeClasses() {
		writeView();
		writePresenter();
		writeFragment();
		writeLayout();
	}

	private void writeView() {
		File folder = new File(inputModel.getProjectPath() + "\\" + inputModel.getViewPackage());
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File file = new File(folder, inputModel.getClassName() + inputModel.getViewSuffix() + ".java");
		FileWriter fw;
		try {

			fw = new FileWriter(file);
			fw.write("package " + inputModel.getPackageName() + "." + inputModel.getViewPackage() + ";"+
					"\n\n" +
					"import com.hannesdorfmann.mosby.mvp.MvpView;\n" +
					"\n" +
					"public interface " + inputModel.getClassName() + inputModel.getViewSuffix() + " extends MvpView {\n" +
					"\n" +
					"}\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writePresenter() {

		File folder = new File(inputModel.getProjectPath() + "\\" + inputModel.getPresenterPackage());
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File file = new File(folder, inputModel.getClassName() + inputModel.getPresenterSuffix() + ".java");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write("package " + inputModel.getPackageName() + "." + inputModel.getPresenterPackage() + ";" +
					"\n\n" +
					"import " + inputModel.getPackageName() + "." + inputModel.getViewPackage() + "." + inputModel.getClassName() + inputModel.getViewSuffix() + ";" + "\n" +
					"import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;\n" +
					"\n" +
					"public class " + inputModel.getClassName() + inputModel.getPresenterSuffix() + " extends MvpBasePresenter<" + inputModel.getClassName() + inputModel.getViewSuffix() + "> {\n" +
					"}\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeFragment() {

		File folder = new File(inputModel.getProjectPath() + "\\" + inputModel.getActivityPackage());
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File file = new File(folder, inputModel.getClassName() + inputModel.getActivitySuffix() + ".java");
		FileWriter fw;

		try {
			fw = new FileWriter(file);
			fw.write("package " + inputModel.getPackageName() + "." + inputModel.getActivityPackage() + ";" +
					"\n\n" +
					"import android.os.Bundle;\n" +
					"import android.support.annotation.NonNull;\n" +
					"import android.support.annotation.Nullable;\n" +
					"import android.view.LayoutInflater;\n" +
					"import android.view.View;\n" +
					"import android.view.ViewGroup;" +
					"import android.support.annotation.NonNull;\n" +
					"\n" +
					"import " + inputModel.getPackageName() + ".R;\n" +
					"import " + inputModel.getPackageName() + "." + inputModel.getViewPackage() + "." + inputModel.getClassName() + inputModel.getViewSuffix() + ";" + "\n" +
					"import " + inputModel.getPackageName() + "." + inputModel.getPresenterPackage() + "." + inputModel.getClassName() + inputModel.getPresenterSuffix() + ";" + "\n" +
					"import com.hannesdorfmann.mosby.mvp.MvpFragment;\n" +

					"\n" +
					"public class " + inputModel.getClassName() + inputModel.getActivitySuffix() + " extends MvpFragment<" + inputModel.getClassName() + inputModel.getViewSuffix() + ", " + inputModel.getClassName() + inputModel.getPresenterSuffix() + ">\n" +
					"\timplements " + inputModel.getClassName() + inputModel.getViewSuffix() + " {\n" +
					"\t\n" +
					"\t@Nullable\n" +
					"\t@Override\n" +
					"\tpublic View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
					"\t\tView view = inflater.inflate(R.layout." + inputModel.getLayoutName() + ", container, false);\n" +
					"\t\treturn view;\n" +
					"\t}" +
					"\t\n" +
					"\t@NonNull\n" +
					"\t@Override\n" +
					"\tpublic " + inputModel.getClassName() + inputModel.getPresenterSuffix() + " createPresenter() {\n" +
					"\t\treturn new " + inputModel.getClassName() + inputModel.getPresenterSuffix() + "();\n" +
					"\t}\n" +
					"}\n");

			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeLayout() {
		int index;
		if ((index = inputModel.getProjectPath().indexOf("/java/")) == -1) {
			return;
		}
		String path = inputModel.getProjectPath().substring(0, index);
		path += "/res/layout";
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File file = new File(folder, inputModel.getLayoutName() + ".xml");
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			fw.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
					"<LinearLayout\n" +
					"    xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
					"    android:layout_width=\"match_parent\"\n" +
					"    android:layout_height=\"match_parent\"\n" +
					"    android:orientation=\"vertical\">\n" +
					"\n" +
					"</LinearLayout>");

			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
