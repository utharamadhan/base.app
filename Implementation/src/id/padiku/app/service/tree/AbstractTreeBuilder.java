package id.padiku.app.service.tree;

import id.padiku.app.dao.tree.TreeBuilderDAO;
import id.padiku.app.tree.TreeBuilder;
import id.padiku.app.tree.TreeNode;

import java.util.List;

public abstract class AbstractTreeBuilder implements TreeBuilder {
	
	protected abstract String getRootTreeName();
	protected abstract String getRootTreeValue();
	protected abstract String getTreeName();
	
	
	protected TreeBuilderDAO treeBuilderDAO ;
	
	public List<? extends TreeNode> buildTrees(Object[] treeParams) { return null ; }
	
	public List<? extends TreeNode> buildTree(Long treeParam) {
		List<? extends TreeNode> nodes = treeBuilderDAO.buildTreeNodes(new Long(getRootTreeValue()));
        return nodes;
	}
	
	public void setTreeBuilderDAO(TreeBuilderDAO treeBuilderDAO) {
		this.treeBuilderDAO = treeBuilderDAO;
	}

}
