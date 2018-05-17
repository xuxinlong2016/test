
/**  
**    ==================================================================================================  
**    ClassName  ��treeview
**    Intro      ��
**    Example    ��  
      Ver        �� 0.1
    ---------------------------------------------------------------------------------------------------  
  
     <div id="tree"></div>
     <SCRIPT LANGUAGE="JavaScript">
     <!--
        var tv = new treeview('id','path',true,true,100);

     //-->
    < /SCRIPT>
  
    ---------------------------------------------------------------------------------------------------  
**    Author    ��ttyp  
**    Email     ��ttyp@21cn.com  
**    Date      ��2007-5-31
**    ==================================================================================================  
**/ 

	function treeview(id,path,check,drag)
	{
		var me			= this;
		var target		= null;
		this.nodes		= new node("root","roottag").nodes;
		this.id			= id?id:"";
		this.icon		= true;
		this.selected	= null;
		this.path		= path?path:"";
		this.selected	= null;
		this.dragTo		= null;
		this.dragFrom	= null;
		this.drag		= drag?true:false;
		this.check		= check?true:false;

		var icons = {
			root	:'root.gif',
			open	:'open.gif',
			close	:'close.gif',
			file	:'file.gif',
			rplus	:'Rplus.gif',
			rminus	:'Rminus.gif',
			join	:'T.gif',
			joinbottom:'L.gif',
			jointop	:'R.gif',
			plus	:'Tplus.gif',
			plusbottom:'Lplus.gif',
			minus	:'Tminus.gif',
			minusbottom:'Lminus.gif',
			blank	:'blank.gif',
			line	:'I.gif'
		};

		this.Ficons = {};

		for(i in icons)
		{
			this.Ficons[i]= new Image()
			this.Ficons[i].src= path + "/" + icons[i];
		}

		this.create		= function(oTarget)
		{	
			if(oTarget){
				for(var i=0;i<this.nodes.length;i++){
					oTarget.appendChild(this.nodes.items[i].toString(me));					
				}
				target = oTarget;
			}
		}
		this.add = function(oo)
		{
			this.insert(this.nodes.length,oo);
		}
		this.insert = function(index,oo)
		{
			if(index>=0&&index<=this.nodes.length)
			{
				var pre = null;
				if(index<this.nodes.length){
					pre = this.nodes.items[index].baseNode;
				}

				this.nodes.insert(index,oo);			
				if(target)
				{
					if(index==this.nodes.length)
					{
						target.appendChild(oo.toString());
					}
					else
					{
						if(pre)
						{
							target.insertBefore(oo.toString(),pre);
						}
					}
				}
			}
		}
	}

	//event
	treeview.prototype.onnodeclick		= function(sender){return false;}
	treeview.prototype.onnodeexpand		= function(sender){}
	treeview.prototype.onnodecollapse	= function(sender){}
	treeview.prototype.onnodedblclick	= function(sender){sender.toggle();return false;}
	treeview.prototype.onnodeafteredit	= function(sender,oldstr){}
	treeview.prototype.onnodebeforeedit = function(sender,oldstr,newstr){}
	treeview.prototype.onnodedrag		= function(from,to){}
	treeview.prototype.onnodekeydown	= function(sender,e){		
		var e=e||event;
		switch(e.keyCode){
			case 61:	//fx +
			case 187:	//=
			case 107:	//number +
				sender.expand();
				break;
			case 109:	//number -
			case 189:	//-
				sender.collapse();
				break;
			case 38:	//up				
				if(sender.previous()){
					sender.previous().select();
				}else{
					if(sender.parent&&sender.parent.level>=0){
						sender.parent.select();
					}
				}
				break;
			case 40:	//down				
				if(sender.next()){
					sender.next().select();
				}
				break;
			case 37:	//left				
				if(sender.parent&&sender.parent.level>=0){
					sender.parent.select();
				}
				break;
			case 39:	//right				
				if(sender.firstChild()){
					sender.firstChild().select();
				}else{
					if(sender.next()){
						sender.next().select();
					}					
				}
				break;
		}
	}
	treeview.prototype.onnodemousedown	= function(sender,e){}
	treeview.prototype.onnodemouseup	= function(sender,e){}
	treeview.prototype.oncontextmenu	= function(sender){}
	treeview.prototype.onnodecheck		= function(sender){}
	treeview.prototype.onselectchange	= function(before,after){}

	//class node
	//node(caption,title,url,target,tag,callback) 

	//caption�ǽڵ���� 
	//title����ʾ 
	//url�ǵ�ڵ��Ǵ��������� 
	//target���������ڵĿ���� 
	//tag�ǽڵ�ĸ���ֵ,���Ա�����ֶ��� 
	//callback�ǵ�ڵ����õĺ���,�첽����ʱ�Ľӿ�
	function node(caption,title,url,target,tag,callback,check)
	{
		var me		= this;
		this.nodes	= new nodes();
		this.target		= target?target:"";
//		this.id		= getid();
		this.caption= caption;
//		this.title	= title?title:caption;
		this.title = caption + ' '+ title;  //sparta 10/04/28
		this.parent = null;
		this.tag	= tag?tag:"";
		
//		this.title=this.title+" ID:"+this.tag;
		//sparta 10/04/28 ����������Ժ����ã�����ֱ�ӷ���Struts2��Action
		this.action	= url?url:"";
		this.loaded = false;		
		this.level  = -1;
		this.isLast = false;
		this.opened = false;
		this.showed = false;
		this.checked= check?true:false;
		this.indent	= [];
	//			alert(caption)

		
		var contol		= null;	
		this.container	= document.createElement("div");

		this.baseNode	= null;
		this.handleNode	= null;
		this.folderNode = null;
		this.textNode	= null;
		this.checkNode	= null;

		//get the id by rand
		function getid(){
			var a = Math.random() + "";
			var b = Math.random() + "";
			var c = Math.random() + "";
			var d = Math.random() + "";

			return a.substr(2,8) + "-" + b.substr(2,8) + "-" + c.substr(2,8) + "-" + d.substr(2,8);			
		}
		//node collection
		function nodes()
		{
			var n = [];
			//get the lenght of the collection
			this.length = {valueOf:function(){return n.length;},toString:function(){return n.length;}}

			this.toString = function()
			{
				  return n.toString();
			}	
			//insert a node at index
			this.insert = function(index,oo)
			{
				if(index>=0&&index<=n.length)
				{
					var ol = n.length;
			
					oo.parent = me;
					oo.level  = me.level+1;
					
					if(index>=n.length)
					{
						oo.isLast = true;
						if(n.length>0)
						{
							n[n.length-1].isLast = false;
							n[n.length-1].setIndent(n[n.length-1].level,n[n.length-1].isLast);
							n[n.length-1].refresh(0);
						}
					}else
					{
						oo.isLast = false;
					}
					oo.indent	= me.indent.concat();
					oo.indent[oo.indent.length]=oo.isLast;

					if(oo.nodes.length>0)
					{
						refreshchildindent(oo);
					}

					function refreshchildindent(node)
					{
						for(var i=0;i<node.nodes.length;i++)
						{
							node.nodes.items[i].indent = node.indent.concat();
							node.nodes.items[i].indent[node.nodes.items[i].indent.length] = node.nodes.items[i].isLast;
							if(node.nodes.items[i].nodes.length>0)
							{
								arguments.callee(node.nodes.items[i]);
							}
						}
					}

					if(me.loaded)
					{			
						if(index==n.length)
						{
							me.container.appendChild(oo.toString());
						}
						else
						{
							var pre = n[index].baseNode;
							if(pre)
							{
								me.container.insertBefore(oo.toString(),pre);
							}
						}
					}
					n.splice(index,0,oo);
					if(ol==0)
					{						
						me.refresh(0);
					}
				}

				return me;
			}
			//add a node after the last node
			this.add = function(oo){		
				return this.insert(n.length,oo);
			}
			//remove a node by node
			this.remove = function(node){
				if(node){
					for(var i=0;i<n.length;i++){
						if(n[i] ==node){
							this.removeAt(i);
						}
					}
				}
			}
			//remove a node at index
			this.removeAt = function(index){
				if(index>=0&&index<n.length){					
					if(index>0&&index==n.length-1){
						n[index-1].isLast = true;
						n[index-1].setIndent(n[index-1].level,n[index-1].isLast);
					}
					n[index].nodes.removeChildren();

					var c = n[index];
					if(c.showed){
						if(control){
							if(c==control.selected){
								if(index>0){
									control.selected = c.previous();
								}else{
									control.selected = c.next();
								}
								if(!control.selected){
									if(c.parent.level>=0){
										control.selected = c.parent;
									}									
								}
								if(control.selected)control.selected.select();
							}
						}
						var oNode = c.baseNode;
						if (oNode) {oNode.parentNode.removeChild(oNode); }
					}
					n.splice(index,1);			
					
					if(n.length>0){
						var o = n[n.length-1];
							o.isLast = true;
							o.setIndent(o.level,o.isLast);

						if(o.showed){o.refresh(0);}
					}else{
						me.loaded = false;
						me.opened = false;
						me.container.style.display = "none";
						me.refresh(0);
					}					
				}
			}				
			this.removeChildren = function (){
				for(var i=n.length-1;i>=0;i--){
					var o = me.nodes.items[i];
						o.nodes.removeChildren();
					if(o.showed){
						if(control){
							if(o==control.selected){
								control.selected = this;
							}
						}
						var oNode = me.baseNode;
						if (oNode) {oNode.parentNode.removeChild(oNode); }
					}
				}		
				n.length = 0;
			}
			this.items = n;
		}
		this.add = function(oo){
			return this.nodes.add(oo);
		}
		this.insert = function(index,oo){
			return this.nodes.insert(index,oo);
		}
		this.remove = function(){
			this.parent.nodes.remove(this);
		}
		//set the caption of the node
		this.setCaption = function(sText,sTitle){
			if(this.showed){
				var oNode = this.textNode;
					if(oNode){
						oNode.firstChild.innerHTML	= sText;
						oNode.title					= typeof(sTitle)!="undefined"?sTitle:sText;
					}
			}
			this.caption = sText;
		}
		//get then deepth of the node
		this.deepth = function(){
			var i = -1;
			var p = me;
			do{
				p = p.parent;
				i++;
			}while(p!=null);

			return i;
		}
		//get root node
		this.root = function(){
			var p = me;
			do{
				if(p.level>0){
					p = p.parent;
				}else{
					break;
				}
			}while(p!=null);			
			return p;
		}

		this.toString = function(tv)
		{

			if(tv){
				control = tv;
			}

			var oNode			= document.createElement("div");
				document.body.appendChild(oNode);

				oNode.className = "Node";
				oNode.noWrap	= true;
				oNode.onselectstart = function(e){ e = e||event;return typeof(e.srcElement.type)!="undefined"&&e.srcElement.type=="text";}
				oNode.oncontextmenu = function(e){ return treeview.prototype.oncontextmenu(me);}

				for(var i=0;i<this.indent.length-1;i++){
					var iIcon = document.createElement("img");
						iIcon.align = "absmiddle";
						iIcon.src	= this.indent[i]?control.Ficons.blank.src:control.Ficons.line.src;

					oNode.appendChild(iIcon);
				}

			var hIcon			= document.createElement("img");
				oNode.appendChild(hIcon);

				hIcon.align		= "absmiddle";						
				hIcon.src		= me.nodes.length>0?(me.opened?(me.level>0?(me.isLast?control.Ficons.minusbottom.src:control.Ficons.minus.src):(me.isLast?control.Ficons.minusbottom.src:control.Ficons.rminus.src)):(me.level>0?(me.isLast?control.Ficons.plusbottom.src:control.Ficons.plus.src):(me.isLast?control.Ficons.plusbottom.src:control.Ficons.rplus.src))):(me.level>0?(me.isLast?control.Ficons.joinbottom.src:control.Ficons.join.src):me.isLast?control.Ficons.joinbottom.src:control.Ficons.join.src);
				hIcon.onclick	= function(){me.toggle();};
				

			var fIcon			= document.createElement("img");
				oNode.appendChild(fIcon);
				fIcon.align		= "absmiddle";
				fIcon.src		= me.icon?this.icon:(me.nodes.length>0?(me.opened?control.Ficons.open.src:control.Ficons.close.src):control.Ficons.file.src);
				//sparta ������һ��ע�͵���Ŀ����ʹ�ļ���ͼƬ����Ӧ�����¼���
				//fIcon.onclick	= function(e){ me.select();return treeview.prototype.onnodeclick(me);};
				fIcon.ondblclick= function(e){ return treeview.prototype.onnodedblclick(me);};

			if(control&&control.check){

				var iCheck			= document.createElement("input");
					iCheck.type		= "checkbox";
					iCheck.checked	= me.checked;
					if(me.checked){
					var vc = document.createAttribute("checked");
						vc.value = "true";
					iCheck.attributes.setNamedItem(vc);
					}
					iCheck.onclick	= function(){me.checked=!me.checked; treeview.prototype.onnodecheck(me);}
					oNode.appendChild(iCheck);

				this.checkNode = iCheck;
			}

			var eText			= document.createElement("span");
				oNode.appendChild(eText);
			var eA				= document.createElement("a");
				eText.appendChild(eA);

				eA.innerHTML	= me.caption?me.caption	:"";
				eA.target		= me.target?me.target	:"";
				//sparta 10/05/04 ����"+ '?treeId=' + me.tag"�ִ�����ִ��ActionʱЯ����Ҫ�Ĳ���(�ڵ�id�ͽڵ�����)
				eA.href			= me.action?me.action + "?treeId=" + me.tag + "&nodeName=" + me.caption :"";
			//	eA.onkeydown	= function(e){ return treeview.prototype.onnodekeydown(me,e);}
			    //sparta �������ondblclick �ͷ��˳�����ԭ����ע�͵��ġ�),���Խڵ�������Ӧ˫���¼���
			    eA.ondblclick	= function(e){ return treeview.prototype.onnodedblclick(me);}
			//	eA.onmousedown  = function(e){ return treeview.prototype.onnodemousedown(me)}
			//	eA.onmousedown  = function(e){alert(this.href)}
			//	eA.onmouseup	= function(e){ return treeview.prototype.onnodemouseup(me);}
			//sparta �������onclick �ͷ��˳�����ԭ����ע�͵��ġ���
				eA.onclick		= function(e){me.select();return treeview.prototype.onnodeclick(me);};
				eA.onfocus		= function(e){ 
					var before = null;
					if(control){
						before = control.selected;
						if(control.selected)control.selected.unselect();						
						control.selected=me;
					}
					if(before!=me)treeview.prototype.onselectchange(before,me);
					me.select();
				}

				eA.ondragstart	= function(e){if(control.drag){var oData = window.event.dataTransfer;oData.effectAllowed = "move";oData.dropEffect ="none";control.dragFrom = me;control.dragTo=null;}}
				eA.ondragenter 	= function(e){if(control.drag){control.dragTo = me;me.select();}}
				eA.ondragend	= function(e){if(control.drag){if(control.dragFrom!=control.dragTo&&control.dragTo!=null){treeview.prototype.onnodedrag(control.dragFrom,control.dragTo);}}}
				eText.className = "Node-unselect";
				eText.title		= me.title;
				eText.ondragover= function(e){if(control.drag){var oEvent = window.event;oEvent.returnValue = false;}}

				me.container.style.display = me.opened?"":"none";

				oNode.appendChild(me.container);
				me.showed = true;

				this.baseNode	= oNode;
				this.handleNode = hIcon;
				this.folderNode = fIcon;
				this.textNode	= eText;
				return oNode;				
		}
		this.edit = function(){

			if(control.selected!=this)this.select();

			this.textNode.style.display	  =	"none";
			this.textNode.style.className = "Node-unselected";


			var oNode = this.baseNode;						
			var o = document.createElement("input");

				o.type				= "text";
				o.style.borderWidth = "1px";
				o.style.width		= "80px";
				o.value				= this.caption;

				oNode.insertBefore(o,this.textNode);
			
			o.onblur = function(){	
				if(this.value.length==0){
					o.focus();
					return false;
				}

				var oldText = me.text;
				var newText = this.value;				
				
				me.setCaption(this.value);

				this.parentNode.removeChild(this);					

				me.textNode.style.display	= "";
				me.textNode.className = "Node-selected";

				//fire the event
				treeview.prototype.onnodeafteredit(this,oldText,newText);
				//sparta 10/5/27 18:29 ��������һ�����Լ�¼�༭��Ľڵ����ơ�
				document.getElementById('nodeName').value= newText;

				me.select();										
			}

			o.onkeypress = function(e){
				var e = e||event;
				if(e.keyCode==13){
					o.blur();
				}
			}
			try{o.scrollIntoView(false)}catch(e){};
			o.select();
		}

		//expand the node
		this.expand = function(){
			if(this.nodes.length>0){
				if(!this.opened){
					this.opened	= true;
					//if(Global.cookieless==false){
					//	setCookie("Node"+this.id,1);
					//}

					if(!me.loaded){
						var p = me.parent;			
						if(p&&!p.opened&&p.level>=0){
							p.expand();
						}
						this.refresh(1);
						this.loaded=true;
					}else{
						this.refresh(0);
					}
					this.container.style.display = "";
					treeview.prototype.onnodeexpand(this);
				}
			}			
		}
		//collapse the node
		this.collapse = function(){
			this.opened= false;
			treeview.prototype.onnodecollapse(this);
			//if(Global.cookieless==false){
			//	setCookie("Node"+this.id,0);
			//}
			this.container.style.display = "none";
			this.refresh(0);
		}
		this.toggle = function(){
			if(this.nodes.length>0){
				if(!this.opened){
					this.expand();
				}else{
					this.collapse();
				}
			}
		}
		//node is my parent
		this.isParent = function(node){
			var p = me.parent;
			while(p!=null){
				if(p==node){
					return true;
				}
				p = p.parent;
			}
			return false;			
		}
		this.setIndent = function(level,value){
			this.indent[level] = value;
			for(var i=0;i<this.nodes.length;i++){				
				this.nodes.items[i].indent[level] = value;
				if(this.nodes.items[i].showed){					
					var ic = this.nodes.items[i].baseNode.childNodes;				
					for(var j=0;j<this.nodes.items[i].indent.length-1;j++){
						if(ic[j].tagName=="IMG"){
							ic[j].src	= this.nodes.items[i].indent[j]?control.Ficons.blank.src:control.Ficons.line.src;
						}
					}
				}
				this.nodes.items[i].setIndent(level,value);
			}			
		}
		this.select = function(){
			if(this.parent){		
				this.parent.expand();
			}

			var oNode = this.textNode;
				if(oNode){
					oNode.className = "Node-selected";
					var of = oNode.firstChild;
					
					try{of.focus();}catch(e){}
				}						
		}
		this.unselect = function(){
			if(this.showed){
				var oNode = this.textNode;
					if(oNode){
						oNode.className = "Node-unselect";
						oNode.firstChild.blur();
					}
			}
		}

		this.refresh = function(loadchild){
			if(this.showed){
				if(loadchild){
					//reload all children
					for(var j=0;j<this.nodes.length;j++){ 				
						this.container.appendChild(this.nodes.items[j].toString());
						this.container.style.display=this.opened?"":"none";					
					}			
				}
				//reload me
				if(this.handleNode){
					this.handleNode.src = me.nodes.length>0?(me.opened?(me.level>0?(me.isLast?control.Ficons.minusbottom.src:control.Ficons.minus.src):(me.isLast?control.Ficons.minusbottom.src:control.Ficons.rminus.src)):(me.level>0?(me.isLast?control.Ficons.plusbottom.src:control.Ficons.plus.src):(me.isLast?control.Ficons.plusbottom.src:control.Ficons.rplus.src))):(me.level>0?(me.isLast?control.Ficons.joinbottom.src:control.Ficons.join.src):me.isLast?control.Ficons.joinbottom.src:control.Ficons.join.src);
				}
				if(this.folderNode){
					if (!this.icon){
						this.folderNode.src = this.nodes.length>0?(this.opened?control.Ficons.open.src:control.Ficons.close.src):control.Ficons.file.src;
					}
				}
			}
		}
		//move to be my child
		this.moveToChild = function(node){
			if(node){
				if(this.isParent(node)==false){
					if(this.opened){
						this.expand();
					}
					var c = node.clone();
							node.remove();

					this.add(c);				
				}
			}
		}
		this.clone = function(){
			var c = new node(this.caption,this.title,this.url,this.target,this.tag);
				copy(this,c);				
				function copy(src,obj){
					for(var i=0;i<src.nodes.length;i++){
						var s = src.nodes.items[i];
						var z = new node(s.caption,s.title,s.url,s.target,s.tag);
							z.loaded = false;							
							obj.add(z);
						if(s.nodes.length){
							arguments.callee(s,z);
						}
					}		
				}
				return c;
		}
		this.firstChild = function(){
			if(this.nodes.length>0){
				return this.nodes.items[0];
			}else{
				return null;
			}
		}
		this.lastChild = function(){
			if(this.nodes.length>0){
				return this.nodes.items[this.nodes.length-1];
			}else{
				return null;
			}
		}
		this.next = function(){
			var p = this.parent;
			for(var i=0;i<p.nodes.length;i++){
				if(p.nodes.items[i] == this){
					if(i<p.nodes.length-1){
						return p.nodes.items[i+1];
					}
				}
			}
			return null;
		}
		this.previous = function(){
			var p = this.parent;
			for(var i=0;i<p.nodes.length;i++){
				if(p.nodes.items[i] == this){
					if(i>0){
						return p.nodes.items[i-1];
					}
				}
			}
			return null;
		}
	}
//	function node(caption,title,url,target,tag,callback,check){


		treeview.prototype.onnodeclick = function(sender){
			//sparta 10/04/29 ��������һ����䣬��ʵ�ֽڵ�ɾ����
			document.getElementById('treeId').value= sender.tag;
						
//			actionStr = 'displayNodeInfo?treeId=' + sender.tag;
//			document.getElementById('costFactoryTreeForm').action = actionStr;
//			document.getElementById('costFactoryTreeForm').submit();		
//			alert(parent.document.getElementById('treeNodeId').value);	
//			alert(document.getElementById('treeId').value);
			//sparta 10/05/03 16:01��������һ����䣬��ʵ�ֽڵ㶨��ҳ��(nodeDefine-*.jsp)�ļ���
//			parent.getElementById('workArea').getElementById('nodeId').value = sender.tag;
//			alert(parent.workArea.name);
//			window.parent.window.workArea.getNodeId(sender.tag);
//			alert(parent.parent.parent.parent.document.nodeId);
			
//			alert(sender.tag);
//			alert(sender.tag);
//			alert(document.getElementById('treeId').value);
//			alert("caption:" + sender.caption + ",id:" + sender.id + ",deepth:" + sender.level + ",tag:" + sender.tag);
			//alert("islast:" + sender.isLast +",indent:" + sender.indent);		
//			alert(sender.caption);
//			alert(document.getElementById("tree").outerHTML);
//alert(sender.target + "  "+ sender.action);
//				window.opener.document.getElementById(sender.target).src = sender.action;
//			return false;
		}
		
		//sparta 10/5/27 17:52 ��������ķ�������֧��treeView��Ӧ˫���¼��ԶԽڵ����ƽ��б༭
		treeview.prototype.onnodedblclick = function(sender){
			sender.edit();
		}
		
		//sparta 10/5/27 17:52 ��������ķ������Լ�¼�ڵ�༭�����ֵ
	//	treeview.prototype.onnodebeforeedit = function(sender,oldstr,newstr){
		//	document.getElementById('nodeName').value= sender.oldstr;
		//}
		
		//sparta 10/5/27 17:52 ��������ķ������Լ�¼�ڵ�༭�����ֵ
	//	treeview.prototype.onnodeafteredit = function(sender,oldstr){
		//	document.getElementById('nodeName').value= sender.oldstr;
	//	}
		
		treeview.prototype.onnodecheck = function(sender){alert(sender.caption + " selected:" + sender.checked);}
		treeview.prototype.onselectchange = function(before,after){
		//	if(before)
		//		alert(before.caption + "," + after.caption);
		//	else
		//		alert(after.caption);
		}

//		treeview.prototype.onnodekeydown = function(sender,e){var e = event||e;alert(e.keyCode);}
		treeview.prototype.onnodedrag		= function(from,to){
			//sparta 10/04/29 22:26 ����������䣬��ʵ�ֽڵ��϶�ͬʱ�������ݿ�
//			alert(from.tag); // from��to���Ƕ��� ��from.tag��������Я������Ϣ
//			alert(to.tag);
			document.getElementById('fromNode').value = from.tag;
			document.getElementById('targetNode').value = to.tag;
			to.moveToChild(from);
			//sparta 10/4/30 16:15 ���� ������Ӧ�ڵ��϶�Action
			document.getElementById('costFactoryTreeForm').action = 'dragDropNode';
		    document.getElementById('costFactoryTreeForm').submit();
		}

		//var i = 1;
        //sparta 10/5/28 10:07 �����������ݣ�����Ӧ����Ҽ�
        treeview.prototype.oncontextmenu	= function(sender){
//          alert(document.getElementById('rightNode').value);
        	if(document.getElementById('rightNode').value.length <= 0){
        		document.getElementById('rightNode').value = sender.tag;
//       alert("xxxx"+document.getElementById('rightNode').value);
//        		alert(sender.tag);
//        		document.getElementById('nodeInfoForm').action = 'nodeInfo?rightNode='+sender.tag;
//        		alert(document.getElementById('nodeInfoForm').action);
//        		document.getElementById('nodeInfoForm').action = 'nodeInfo';
//        		document.getElementById('nodeInfoForm').submit();
        		disDiv(sender.tag); return(false);
        	}

        }


	 function Tree_innerHtml_init()
		{
      for(var k =0; k < 10; k++)
      {
      var areaArray=document.getElementsByTagName("IMG") ;
      var Length =areaArray.length;
      for(var i =0; i < Length; i++)
       {
        obj_img=areaArray[i];
        if(obj_img.src.indexOf('plus.gif')>1)
        {
     //   alert(obj_img.src);
        obj_img.onclick();
        }
        }
      }
      for(var k =0; k < 10; k++)
      {
      var areaArray=document.getElementsByTagName("IMG") ;
      var Length =areaArray.length;
      for(var i =0; i < Length; i++)
       {
        obj_img=areaArray[i];
        if(obj_img.src.indexOf('minus.gif')>1)
        {
     //   alert(obj_img.src);
        obj_img.onclick();
        }
        }
      }
    }
    
   	function Tree_innerHtml_treate()
		 {
		 var reg = /[\r\n]/g;
		 Tree_innerHtml_init();
		 var Tree_innerHtml=document.getElementById('tree').innerHTML.replace(/<IMG align=absMiddle src=/g,'').replace(/file:\/\/\/D:\/html-��Ч\/tree\/tree\//g,'').replace(/t.gif/g,'').replace(/blank.gif/g,'').replace(/blank.gif/g,'').replace(/rplus.gif/g,'').replace(/lplus.gif/g,'').replace(/I.gif/g,'').replace(/l.gif/g,'').replace(/CHECKED /g,'').replace(/<INPUT type=checkbox>/g,'').replace(/ width=16 height=22/g,'').replace(/class=Node-unselect /g,'').replace(/ class=Node noWrap/g,'').replace(/<DIV style=\"DISPLAY: none\">/g,'').replace(/>\"\"/g,'')

		 var   arr_innerHtml   =   Tree_innerHtml.split('<DIV>');   
		 Tree_innerHtml="";
		 var steps=0;
		 for(var i =1; i < arr_innerHtml.length; i++)  
		 {
		 Tree_innerHtml=Tree_innerHtml+steps+"--<DIV>"+arr_innerHtml[i]
		 
		 if(arr_innerHtml[i].length>arr_innerHtml[i].replace("close.gif","").length)
		    steps=steps+1
		 else if(arr_innerHtml[i].length-arr_innerHtml[i].replace(/<\/DIV><\/DIV>/g,"").length==24)
		    steps=steps-1
		 else if(arr_innerHtml[i].length-arr_innerHtml[i].replace(/<\/DIV><\/DIV>/g,"").length==36)
		    steps=steps-2
		 else if(arr_innerHtml[i].length-arr_innerHtml[i].replace(/<\/DIV><\/DIV>/g,"").length==48)
		    steps=steps-3
		 else if(arr_innerHtml[i].length-arr_innerHtml[i].replace(/<\/DIV><\/DIV>/g,"").length==60)
		    steps=steps-4
		 else if(arr_innerHtml[i].length-arr_innerHtml[i].replace(/<\/DIV><\/DIV>/g,"").length==72)
		    steps=steps-5
		 else if(arr_innerHtml[i].length-arr_innerHtml[i].replace(/<\/DIV><\/DIV>/g,"").length==84)
		    steps=steps-6
		 else if(arr_innerHtml[i].length-arr_innerHtml[i].replace(/<\/DIV><\/DIV>/g,"").length==96)
		    steps=steps-4
		 
		 
		 }
		 return Tree_innerHtml
    }
   	
   
