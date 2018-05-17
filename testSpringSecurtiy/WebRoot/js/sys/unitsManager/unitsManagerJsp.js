
//删除节点
function onDelete(actionUrl){
	document.getElementById('unitsTreeForm').action = actionUrl;
	document.getElementById('unitsTreeForm').submit();
}

//前插兄弟节点
function onInsertPrevious(actionUrl){
	document.getElementById('unitsTreeForm').action = actionUrl;
	document.getElementById('unitsTreeForm').submit();
}

//后插兄弟节点
function onInsertNext(actionUrl){
	document.getElementById('unitsTreeForm').action = actionUrl;
	document.getElementById('unitsTreeForm').submit();
}

//插入子节点
function onInsertChild(actionUrl){
	document.getElementById('unitsTreeForm').action = actionUrl;
	document.getElementById('unitsTreeForm').submit();
}

//节点编辑
function onNodeEdit(actionUrl){
	document.getElementById('unitsTreeForm').action = actionUrl;
	document.getElementById('unitsTreeForm').submit();
}


//增加子节点
function addChild()
{
	var nn = tv.selected;
	if(!nn)nn=tv;

	nn.add(new node("新增子节点","新增子节点测试","http://10.67.112.3","workArea"));
}

//前插
function insertPrevious(nn)
{
	var nn = tv.selected;
	if(!nn)nn=tv;

    nn.insert(nn.nodes.length-1, new node("前插节点"));		
}

//后插
function insertNext(nn)
{
	var nn = tv.selected;
	if(!nn)nn=tv;

    nn.insert(nn.nodes.length, new node("后插节点"));		
}

//确定是否删除
function ensure() 
{//利用对话框返回的值（true 或者 false） 
    if(confirm("你确定要删除该节点及子节点？")) 
    { 
         return true; 
    }else { 
         return false; 
    } 
} 


    //sparta 10/7/1 9:18 用户点击之后，当再次刷新时，利用下面两个变量保留该节点的展开状态
	var nodeId = document.getElementById('treeId').value;
	var nodeExpand ;
	
	var tree_node_array=tree_nodes.split(";"); 
	for(var i=0;i<tree_node_array.length;i++)	
	{
		var node_data=tree_node_array[i].split(","); 
		if(node_data[0].length==4)
		{
			var root = new node(node_data[1],node_data[2],node_data[3],"",node_data[0]);
			tv.add(root);
			
			//sparta add  记录下用户点击后的单位节点
			if(node_data[0] == nodeId){
				nodeExpand = root;
			}

		}
		if(node_data[0].length==8)
		{
			var leaf_1  = new node(node_data[1],node_data[2],node_data[3],"",node_data[0]);
			root.add(leaf_1);
			
			//sparta add 
			if(node_data[0] == nodeId){
				nodeExpand = leaf_1;
			}
			
		}
		
		if(node_data[0].length==12)
		{
			var leaf_2  = 
				new node(node_data[1],node_data[2],
						node_data[3],"",node_data[0]);
			leaf_1.add(leaf_2);
			
			//sparta add 
			if(node_data[0] == nodeId){
				nodeExpand = leaf_2;
			}
			
		}
		if(node_data[0].length==16)
		{
			var leaf_3  = new node(node_data[1],
					node_data[2],node_data[3],"",node_data[0]);
			leaf_2.add(leaf_3);
			
			//sparta add 
			if(node_data[0] == nodeId){
				nodeExpand = leaf_3;
			}
			
		}
		if(node_data[0].length==20)
		{
			var leaf_4  = new node(node_data[1],
					node_data[2],node_data[3],"",node_data[0]);
			leaf_3.add(leaf_4);
			
			//sparta add 
			if(node_data[0] == nodeId){
				nodeExpand = leaf_4;
			}
			
		}
		if(node_data[0].length==24)
		{
			var leaf_5  = new node(node_data[1],
					node_data[2],node_data[3],"",node_data[0]);
			leaf_4.add(leaf_5);
			
			//sparta add 
			if(node_data[0] == nodeId){
				nodeExpand = leaf_5;
			}
			
		}
		if(node_data[0].length==28)
		{
			var leaf_6  = new node(node_data[1],
					node_data[2],node_data[3],"",node_data[0]);
			leaf_5.add(leaf_6);
			
			//sparta add 
			if(node_data[0] == nodeId){
				nodeExpand = leaf_6;
			}
			
		}
		
		 
		if(node_data[0].length==32)
		{
			var leaf_7  = new node(node_data[1],
					node_data[2],node_data[3],"",node_data[0]);
			leaf_6.add(leaf_7);
			
			//sparta add 
			if(node_data[0] == nodeId){
				nodeExpand = leaf_7;
			}
			
		}
		
		if(node_data[0].length==36)
		{
			var leaf_8  = new node(node_data[1],
					node_data[2],node_data[3],"",node_data[0]);
			leaf_7.add(leaf_8);
			
			//sparta add 
			if(node_data[0] == nodeId){
				nodeExpand = leaf_8;
			}
			
		}
	
	}
	tv.create(document.getElementById("tree"));
	//sparta 10/6/30 14:20 初进页面时，仅展开父节点，当用户点击某节点时，展开该节点的父节点。	
	if(nodeExpand) {
		nodeExpand.parent.expand();
	}else{
		root.expand();
	}
		






    
		




