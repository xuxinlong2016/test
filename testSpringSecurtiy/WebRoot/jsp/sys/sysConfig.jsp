<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<%@ taglib prefix="s" uri="/struts-tags" %> 

<%@ taglib prefix="ss" uri="/struts-dojo-tags" %>

<!--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">-->
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>Untitled Page</TITLE>
<META content="text/html; charset=gbk" http-equiv="Content-Type">

<META name="GENERATOR" content="MSHTML 8.00.7600.16490">

	<!-- ����struts2��dojo+ajax���� -->
    <ss:head theme="ajax" debug="true" />
    <script type="text/javascript" charset="gbk">
        
        function treeNodeSelected(arg) {
            loadSysConfigPath(arg.source.widgetId);
        }

        //���ڵ�չ��ʱ�����¼�
        function treeNodeExpanded(arg) {    
            //alert(arg.source.title + ' expanded');    
        }

        //ҳ�����ʱĬ��չ���ڵ�
        function expandObj(obj){
		   if (obj){
			   if (obj.children){
				  for(var i = 0 ; i < obj.children.length ; i ++ ){
					 var childObj = obj.children[i];
					  if (childObj){
						 try{
							 childObj.expand();
						 }catch(e){}
						if (childObj.children){
							expandObj(childObj);
						 }
					  }
				  }
			   }
		   }
    	}  
        
        dojo.addOnLoad(function() {              
        	var t = dojo.widget.byId('parentId'); 
        	dojo.event.topic.subscribe(t.eventNames.expand, 'treeNodeExpanded');
			expandObj(t);
        	var s = t.selector;
            dojo.event.connect(s, 'select', 'treeNodeSelected');
        });

        

        //���ݲ�ͬ�Ĳ˵�id��ʾ��ͬ��ϵͳ����ҳ��
        function loadSysConfigPath(id){
            //alert(id);
            //����Ǹ��ڵ㣬����Ӧ
            if ( id != 'parentId_root' ){
            	window.parent.window.document.getElementById('workArea').src = 
            	'${pageContext.request.contextPath}/'+id;
            }
        }
    </script>

</HEAD>

<BODY>

	<div style="float:left; margin-right: 50px;">
	    <s:form id="SysForm" method="post" theme="simple">
    		<ss:tree label="ϵͳ����" id="parentId"
                templateCssPath="/struts/tree.css" showRootGrid="true"
                showGrid="true">
        		<ss:treenode  label="��λ����" id="UnitsManager"/>
        		<ss:treenode  label="�û���ɫ����" id="managerUser"/>
            	<ss:treenode  label="��ɫȨ������" id="managerRole"/>
            	<ss:treenode  label="Ȩ����Դ����" id="managerAuthority" />
            	<ss:treenode  label="��Դ����" id="managerResource" />
    		</ss:tree>
    	</s:form>
	</div>

</BODY>

</html>

