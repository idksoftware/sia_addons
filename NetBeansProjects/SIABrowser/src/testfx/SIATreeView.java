package testfx;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import testfx.MainApp.TextFieldTreeCellImpl;

public class SIATreeView extends TreeView<String> {
	
	private final Node rootIcon = new ImageView(new Image(getClass().getResourceAsStream("/root.png")));
    private final Image folderIcon = new Image(getClass().getResourceAsStream("/folder-16.png"));

    
    public Node getRootIcon() {
    	return rootIcon;
    }
    
    public Image getFolderIcon() {
    	return folderIcon;
    }

    /*
    public TreeItem<String> getRootItem() {
    	//TreeItem<String> rootItem = new TreeItem<String> ("Photos", rootIcon);
    	rootItem.setExpanded(true);
    	return rootItem;
    }
    
    TreeItem<String> rootItem = new TreeItem<String> ("Photos", rootIcon);
	*/
    
    
	public SIATreeView(TreeItem<String> rootItem) {
		super(rootItem);
		setShowRoot(true);
        setEditable(false);
        setCellFactory(new Callback<TreeView<String>, TreeCell<String>>()
        {
            @Override
            public TreeCell<String> call(TreeView<String> p)
            {
                return new TextFieldTreeCellImpl();
            }
        });

	}
	private final class TextFieldTreeCellImpl extends TreeCell<String>
    {

        private TextField textField;
        private ContextMenu addMenu = new ContextMenu();

        // This is not required
        public TextFieldTreeCellImpl()
        {
            MenuItem addMenuItem = new MenuItem("Add");
            addMenu.getItems().add(addMenuItem);
            addMenuItem.setOnAction(new EventHandler()
            {
                @Override
                public void handle(Event t)
                {
                    TreeItem newEmployee = new TreeItem<String>("New");
                    getTreeItem().getChildren().add(newEmployee);
                }
            });
        }

        @Override
        public void startEdit()
        {
            super.startEdit();

            if (textField == null)
            {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }

        @Override
        public void cancelEdit()
        {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(getTreeItem().getGraphic());
        }

        @Override
        public void updateItem(String item, boolean empty)
        {
            super.updateItem(item, empty);

            if (empty)
            {
                setText(null);
                setGraphic(null);
            }
            else
            {
                if (isEditing())
                {
                    if (textField != null)
                    {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                }
                else
                {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                    if (!getTreeItem().isLeaf() && getTreeItem().getParent() != null)
                    {
                        setContextMenu(addMenu);
                    }
                }
            }
        }

        private void createTextField()
        {
            textField = new TextField(getString());
            textField.setOnKeyReleased(new EventHandler<KeyEvent>()
            {

                @Override
                public void handle(KeyEvent t)
                {
                    if (t.getCode() == KeyCode.ENTER)
                    {
                        commitEdit(textField.getText());
                    }
                    else if (t.getCode() == KeyCode.ESCAPE)
                    {
                        cancelEdit();
                    }
                }
            });

        }

        private String getString()
        {
            return getItem() == null ? "" : getItem().toString();
        }
    }

	

}
