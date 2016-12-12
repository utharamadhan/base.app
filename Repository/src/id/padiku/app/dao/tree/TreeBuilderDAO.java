package id.padiku.app.dao.tree;

import id.padiku.app.exception.SystemException;
import id.padiku.app.tree.TreeNode;

import java.util.List;

public interface TreeBuilderDAO {
	
	public List<? extends TreeNode> buildTreeNodes(Long param) throws SystemException;
	
	public List<? extends TreeNode> buildTreeNodes(Object[] param) throws SystemException;
	
	public List<? extends TreeNode> buildTreeNodes() throws SystemException;

	public String getRootTreeName() throws SystemException;

	public String getRootTreeValue() throws SystemException;
	
}
