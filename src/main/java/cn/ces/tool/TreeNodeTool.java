package cn.ces.tool;

public class TreeNodeTool {
	/**
	 * �ݹ����ѭ������easyuitree��ʾ��json���ݸ�ʽ,checked����˵��Ƿ�ѡ��
	 * treeNode ��ʾ�����β˵���ʽ
	 * id ��ǰ�˵���id
	 * prarentMenuId ��ǰ�˵��ĸ��˵���id
	 * name ģ�������
	 * url �˵���ַ
	 * checked �Ƿ�ѡ��
	 */
	public static TreeNode setTreeNode(TreeNode treeNode, int id, int prarentMenuId,String name,String url,boolean checked) {
		//��treeNodeΪ��ʱ����menu����treeNode��
		if(treeNode == null){
			treeNode = new TreeNode();
			treeNode.setId(String.valueOf(id));
			treeNode.setText(name);
			AttributesBean attributes = new AttributesBean();
			attributes.setUrl(url);
			treeNode.setAttributes(attributes);
		}else{
			//���treeNode��Ϊ�ղ��ҵ�ǰ�˵���treeNode�нڵ���Ӳ˵����򽫵�ǰ�˵����뵽treeNode�ĺ��ӵ���
			String tid = String.valueOf(treeNode.getId());
			if(String.valueOf(prarentMenuId).equals(tid)){
				TreeNode tNode = new TreeNode();
				tNode.setId(String.valueOf(id));
				tNode.setText(name);
				tNode.setChecked(checked);
				AttributesBean ab = new AttributesBean();
				ab.setUrl(url);
				
				tNode.setAttributes(ab);
				
				treeNode.getChildren().add(tNode);
			}else{
				//ѭ������treeNode���Ƿ��е�ǰ�˵��ĸ��˵�
				for(TreeNode tn : treeNode.getChildren()){
					setTreeNode(tn,id,prarentMenuId,name,url,checked);
				} 
			}
		}
		return treeNode;
	}
}
