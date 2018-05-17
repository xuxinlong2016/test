<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<%@ taglib prefix="s" uri="/struts-tags" %> 

<%@ taglib prefix="ss" uri="/struts-dojo-tags" %>

<!--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">-->
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>Untitled Page</TITLE>
<META content="text/html; charset=gbk" http-equiv="Content-Type">

<META name="GENERATOR" content="MSHTML 8.00.7600.16490">

	<!-- 采用struts2的dojo+ajax画树 -->
    <ss:head theme="ajax" debug="true" />
    <script type="text/javascript" charset="gbk">
        
        function treeNodeSelected(arg) {
            loadSysConfigPath(arg.source.widgetId);
        }

        //当节点展开时触发事件
        function treeNodeExpanded(arg) {    
            //alert(arg.source.title + ' expanded');    
        }

        //页面加载时默认展开节点
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

        

        //根据不同的菜单id显示不同的系统设置页面
        function loadSysConfigPath(id){
            //alert(id);
            //如果是父节点，则不响应
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
    		<ss:tree label="系统设置" id="parentId"
                templateCssPath="/struts/tree.css" showRootGrid="true"
                showGrid="true">
        		<ss:treenode  label="单位设置" id="UnitsManager"/>
        		<ss:treenode  label="用户角色设置" id="managerUser"/>
            	<ss:treenode  label="角色权限设置" id="managerRole"/>
            	<ss:treenode  label="权限资源设置" id="managerAuthority" />
            	<ss:treenode  label="资源设置" id="managerResource" />
    		</ss:tree>
    	</s:form>
	</div>

</BODY>

</html>

