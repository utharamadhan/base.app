package id.padiku.app.service.tree;

import id.padiku.app.dao.business.settings.BusinessSettingsTreeBuilderDAO;
import id.padiku.app.dao.tree.TreeBuilderDAO;
import id.padiku.app.tree.TreeBuilder;
import id.padiku.app.tree.TreeNode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusinessSettingsTreeBuilder implements TreeBuilder {

	@Autowired
	private TreeBuilderDAO businessSettingsTreeBuilderDAO;
	public BusinessSettingsTreeBuilder(){}
	
	public void setBusinessSettingsTreeBuilderDAO(BusinessSettingsTreeBuilderDAO businessSettingsTreeBuilderDAO){
		this.businessSettingsTreeBuilderDAO=businessSettingsTreeBuilderDAO;
	}
	
	@Override
	public List<? extends TreeNode> buildTree(Long treeParam) {
		return null;
	}

	@Override
	public List<? extends TreeNode> buildTree() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends TreeNode> buildTrees(Object[] treeParams) {
		return businessSettingsTreeBuilderDAO.buildTreeNodes(treeParams);
	}

}
