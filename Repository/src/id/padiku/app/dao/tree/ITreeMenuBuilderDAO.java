package id.padiku.app.dao.tree;

import id.padiku.app.exception.SystemException;
import id.padiku.app.tree.TreeNode;

import java.util.List;

public interface ITreeMenuBuilderDAO {
	public List<? extends TreeNode> buildTreeNodes(Object[] param, int pkAppFunctionParent) throws SystemException;
}
