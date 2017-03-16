package drilon;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.ide.util.PropertiesComponentImpl;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class InputForm extends JDialog {

	public final static int TYPE_ACTIVITY = 0;
	public final static int TYPE_FRAGMENT = 1;

	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JTextField className;
	private JTextField viewSuffix;
	private JTextField presenterSuffix;
	private JTextField activitySuffix;
	private JTextField activityPackage;
	private JTextField viewPackage;
	private JTextField presenterPackage;
	private JLabel activitySuffixLabel;
	private JLabel activityPackageLabel;
	private JTextField layoutPrefix;
	private String path;
	private String packageName;

	private ActionListener actionListener;

	private PropertiesComponent propertiesComponent;
	private int type;

	public InputForm() {
		propertiesComponent = PropertiesComponentImpl.getInstance();
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);

		buttonCancel.addActionListener(e -> onCancel());

		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});

		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	public void setOnOkListener(ActionListener actionListener) {
		this.actionListener = actionListener;
		buttonOK.addActionListener(e -> {
			saveComponents();
			this.actionListener.actionPerformed(e);
		});
	}

	private void saveComponents() {
		PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
		propertiesComponent.setValue(Keys.viewPackage.name(), viewPackage.getText());
		propertiesComponent.setValue(Keys.presenterPackage.name(), presenterPackage.getText());
		propertiesComponent.setValue(Keys.viewSuffix.name(), viewSuffix.getText());
		propertiesComponent.setValue(Keys.presenterSuffix.name(), presenterSuffix.getText());
		switch (type) {
			case TYPE_FRAGMENT:
				propertiesComponent.setValue(Keys.fragmentPackage.name(), activityPackage.getText());
				propertiesComponent.setValue(Keys.fragmentSuffix.name(), activitySuffix.getText());
				propertiesComponent.setValue(Keys.fragmentLayoutPrefix.name(), layoutPrefix.getText());
				break;
			case TYPE_ACTIVITY:
				propertiesComponent.setValue(Keys.activityPackage.name(), activityPackage.getText());
				propertiesComponent.setValue(Keys.activitySuffix.name(), activitySuffix.getText());
				propertiesComponent.setValue(Keys.activityLayoutPrefix.name(), layoutPrefix.getText());
				break;
		}
	}

	public InputModel getGeneratedInputModel() {
		InputModel inputModel = new InputModel();
		inputModel.setActivityPackage(activityPackage.getText());
		inputModel.setActivitySuffix(activitySuffix.getText());
		inputModel.setClassName(className.getText());
		inputModel.setPackageName(packageName);
		inputModel.setPresenterSuffix(presenterSuffix.getText());
		inputModel.setPresenterPackage(presenterPackage.getText());
		inputModel.setProjectPath(path);
		inputModel.setViewPackage(viewPackage.getText());
		inputModel.setViewSuffix(viewSuffix.getText());
		inputModel.setLayoutPrefix(layoutPrefix.getText());
		return inputModel;
	}

	public void setPath(String path) {
		this.path = path;
		String androidBasePath = "/src/main/java/";
		String intellijBasePath = "/src/";
		if (path.contains(androidBasePath)) {
			packageName = path.substring(path.indexOf(androidBasePath));
			packageName = packageName.substring(androidBasePath.length());
		} else {
			packageName = path.substring(path.indexOf(intellijBasePath));
			packageName = packageName.substring(intellijBasePath.length());
		}
		packageName = packageName.replaceAll("/", ".");
	}

	public void initLabels(int type) {
		this.type = type;
		viewPackage.setText(propertiesComponent.getValue(Keys.viewPackage.name(), "view"));
		presenterPackage.setText(propertiesComponent.getValue(Keys.presenterPackage.name(), "presenter"));
		viewSuffix.setText(propertiesComponent.getValue(Keys.viewSuffix.name(), "View"));
		presenterSuffix.setText(propertiesComponent.getValue(Keys.presenterSuffix.name(), "Presenter"));

		switch (type) {
			case TYPE_FRAGMENT:
				activitySuffix.setText(propertiesComponent.getValue(Keys.fragmentSuffix.name(), "Fragment"));
				activityPackage.setText(propertiesComponent.getValue(Keys.fragmentPackage.name(), "fragment"));
				activitySuffixLabel.setText("Fragment suffix");
				activityPackageLabel.setText("Fragment package");
				layoutPrefix.setText(propertiesComponent.getValue(Keys.fragmentLayoutPrefix.name(),"fragment"));
				break;
			case TYPE_ACTIVITY:
				activitySuffix.setText(propertiesComponent.getValue(Keys.activitySuffix.name(), "Activity"));
				activityPackage.setText(propertiesComponent.getValue(Keys.activityPackage.name(), "activity"));
				activitySuffixLabel.setText("Activity suffix");
				activityPackageLabel.setText("Activity package");
				layoutPrefix.setText(propertiesComponent.getValue(Keys.activityLayoutPrefix.name(),"activity"));
				break;
		}
	}


	private void onCancel() {
		dispose();
	}

}
