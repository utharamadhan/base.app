package id.padiku.app.service.tree;

import id.padiku.app.dao.report.ReportTreeBuilderDAO;
import id.padiku.app.dao.tree.TreeBuilderDAO;
import id.padiku.app.tree.TreeBuilder;
import id.padiku.app.tree.TreeNode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author antony
 *
 */
@Service
@Transactional
public class ReportTreeBuilder implements TreeBuilder {

	@Autowired
	private TreeBuilderDAO reportTreeBuilderDAO;
	public ReportTreeBuilder(){}
	
	public void setReportTreeBuilderDAO(ReportTreeBuilderDAO reportTreeBuilderDAO){
		this.reportTreeBuilderDAO=reportTreeBuilderDAO;
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
		return reportTreeBuilderDAO.buildTreeNodes(treeParams);
	}

}
