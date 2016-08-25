package TreeView;

import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

@SuppressWarnings("hiding")
public class SIATreeView<Object> extends TreeView<Object> {
	@SuppressWarnings("unchecked")
	public SIATreeView(TreeItem<String> rootItem) {
		super((TreeItem<Object>) rootItem);
	}

	@SuppressWarnings("unchecked")
	public void initialize() {
        this.setCellFactory(tree -> {
            TreeCell<String> cell = new TreeCell<String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
            cell.setOnMouseClicked(event -> {
                if (! cell.isEmpty()) {
                    TreeItem<String> treeItem = cell.getTreeItem();
                    // do whatever you need with the treeItem...
                    //treeItem.setValue("test 1");
                    /*
                    Node node = cell.getParent();
                    node
                    System.out.println(.getItem());
                    */
                    System.out.println(cell.getItem());
                }
            });
            return (TreeCell<Object>) cell ;
        });
    }

}
