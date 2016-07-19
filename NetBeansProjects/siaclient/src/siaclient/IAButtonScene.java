package siaclient;



abstract class IAButtonScene extends IASceneBase {
	
	public IAButtonScene(String buttonId, String title) {
		super(buttonId, title);
	}

	

	protected boolean addScene(IASceneBase sceneBase) {
		
		super.addScene(sceneBase);
		return true;
	}

	
}
