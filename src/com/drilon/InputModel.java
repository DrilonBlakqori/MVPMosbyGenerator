package com.drilon;

public class InputModel {

	private String projectPath;
	private String packageName;
	private String className;
	private String viewPackage;
	private String presenterPackage;
	private String activityPackage;
	private String viewSuffix;
	private String presenterSuffix;
	private String activitySuffix;
	private String layoutPrefix;

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getViewPackage() {
		return viewPackage;
	}

	public void setViewPackage(String viewPackage) {
		this.viewPackage = viewPackage;
	}

	public String getPresenterPackage() {
		return presenterPackage;
	}

	public void setPresenterPackage(String presenterPackage) {
		this.presenterPackage = presenterPackage;
	}

	public String getActivityPackage() {
		return activityPackage;
	}

	public void setActivityPackage(String activityPackage) {
		this.activityPackage = activityPackage;
	}

	public String getViewSuffix() {
		return viewSuffix;
	}

	public void setViewSuffix(String viewSuffix) {
		this.viewSuffix = viewSuffix;
	}

	public String getPresenterSuffix() {
		return presenterSuffix;
	}

	public void setPresenterSuffix(String presenterSuffix) {
		this.presenterSuffix = presenterSuffix;
	}

	public String getActivitySuffix() {
		return activitySuffix;
	}

	public void setActivitySuffix(String activitySuffix) {
		this.activitySuffix = activitySuffix;
	}

	public String getLayoutPrefix() {
		return layoutPrefix;
	}

	public String getLayoutName() {
		return layoutPrefix.toLowerCase() + (className.replaceAll("([A-Z])", "_$1").toLowerCase());
	}

	public void setLayoutPrefix(String layoutPrefix) {
		this.layoutPrefix = layoutPrefix;
	}
}
