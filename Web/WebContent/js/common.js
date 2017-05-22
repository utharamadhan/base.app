var	monthName =	new	Array("January","February","March","April","May","June","July","August","September","October","November","December")

function popupCalendar(formatDate, obj){
	var sURL        = "../script/datepicker/popupCalendar.htm";
	var par         = new Object();
	par.formatDate 	= formatDate;
	par.obj			= obj;
	retvalue = window.showModalDialog(sURL, par, "dialogHeight=13;dialogWidth=15;center=1;status=0;scroll=0;resizable=0;help=0");
	if (retvalue != null){
		obj.value=retvalue;
	}
}

function popupCalendar2(formatDate, obj, webContext){
	var sURL        = webContext+"/script/datepicker/popupCalendar.htm";
	var par         = new Object();
	par.formatDate 	= formatDate;
	par.obj			= obj;
	retvalue = window.showModalDialog(sURL, par, "dialogHeight=13;dialogWidth=15;center=1;status=0;scroll=0;resizable=0;help=0");
	if (retvalue != null){
		obj.value=retvalue;		
	}
}

function popupCalendarCheck(formatDate, obj, obj2, operator){
	var retvalue;
	var sURL = "../script/datepicker/popupCalendar.htm";
	var par = new Object();
	par.formatDate 	= formatDate;
	par.obj			= obj;
	retvalue = window.showModalDialog(sURL, par, "dialogHeight=13;dialogWidth=15;center=1;status=0;scroll=0;resizable=0;help=0");
	if (retvalue!=null){
		//alert("referenceDate :"+obj2.value+" ret value : "+retvalue+" formatDate : "+formatDate);				
		if (!checkDate(retvalue, obj2, formatDate, operator)){
			obj.value="";
			alert("Date not valid. Inserted date must be " + checkDateErrorMessage(obj2.value, operator));			
		}else{
			obj.value=retvalue;
		}
	}
}

function CalendarCheckInterval(formatDate, obj, obj2, operator){
	var retvalue;
	var sURL = "../script/datepicker/popupCalendar.htm";
	var par = new Object();
	par.formatDate 	= formatDate;
	par.obj			= obj;
	retvalue = window.showModalDialog(sURL, par, "dialogHeight=13;dialogWidth=15;center=1;status=0;scroll=0;resizable=0;help=0");
	if (retvalue!=null){
		//if (!checkDateInterval(retvalue, obj2, formatDate, operator)){
		//	textDate.value = "";
		//	alert("Date not valid");			
		//}else{
			obj.value = retvalue;
		//}
	}
}

function checkDateInterval(date1, obj2, format, obj3){
	if ((obj2!=null) ){
			dateFormat=format;
			formatChar = " "
			aFormat	= dateFormat.split(formatChar)
			if (aFormat.length<3)
			{
				formatChar = "/"
				aFormat	= dateFormat.split(formatChar)
				if (aFormat.length<3)
				{
					formatChar = "."
					aFormat	= dateFormat.split(formatChar)
					if (aFormat.length<3)
					{
						formatChar = "-"
						aFormat	= dateFormat.split(formatChar)
						if (aFormat.length<3)
						{
							formatChar=""
							return false;
						}
					}
				}
			}
			
			if ( formatChar	!= "" ){
				var dateSelected=new Array(2);
				var monthSelected=new Array(2);
				var yearSelected=new Array(2);
				var xData=new Array(2);
				xData[0]=date1;
				xData[1]=obj2.value;
				for (var z=0;z<2;z++){
					aData = xData[z].split(formatChar);
					for	(i=0;i<3;i++){
						if ((aFormat[i]=="d") || (aFormat[i]=="dd")){
							dateSelected[z] = parseInt(aData[i], 10)
						}else if((aFormat[i]=="m") || (aFormat[i]=="mm")){
							monthSelected[z] =	parseInt(aData[i], 10) - 1
						}else if	(aFormat[i]=="yyyy"){
							yearSelected[z]= parseInt(aData[i], 10)
						}else if	(aFormat[i]=="mmm"){
							for	(j=0; j<12;	j++){
								if (aData[i]==monthName[j]){
									monthSelected[z]=j
								}
							}
						}
					}
				
				}
			}
		if (obj2.value==""){
			return false;
		}	
		var tgl1 = new Date;
		var tgl2 = new Date;
		var tgl3 = new Date;
		var str_tgl1=new String;
		var str_tgl2=new String;
		
		tgl1=stringToDate(yearSelected[0],monthSelected[0],(dateSelected[0]-1));
	    str_tgl1=yearSelected[0]+"-"+monthSelected[0]+"-"+(dateSelected[0]-1);
	    tgl2=stringToDate(yearSelected[1],monthSelected[1],(dateSelected[1]));
		str_tgl2=yearSelected[1]+"-"+monthSelected[1]+"-"+(dateSelected[1]);
	    
		//alert("obj2: "+obj2.value+"format :"+format.value+"obj3 :"+obj3.value+"date1 :"+date1.value);
		//alert("tgl1: "+str_tgl1+" tgl2: "+str_tgl2);
		if (obj3.value!='0'){
		 	var max=tgl3.getDate() + parseInt(obj3.value);
			tgl3.setDate(max);
		    tgl3=stringToDate(tgl3.getYear(),tgl3.getMonth(),tgl3.getDate()-1);
			if((tgl2>tgl1) || (tgl3<tgl1) ){ 
				return false;
				//return true;
			}
		}else{
			if(tgl2>tgl1){ 
				return false;
			}
		}
	}
	return true;
}

//operator	
//1---Date < Join_date 
//2---Date > Join_date 
//3---Date = Join_date 
//4---Date <= Join_date 
//5---Date >= Join_date 
//6---Date <> Join_date 

function checkDateErrorMessage(referenceDate, operator) {
	switch (operator) {
		case 1 :
			return "less than " + referenceDate;
			break;
		case 2 :
			return "greater than " + referenceDate;
			break;
		case 3 :
			return "same as " + referenceDate;
			break;
		case 4 :
			return "less than or equal to " + referenceDate;
			break;
		case 5 :
			return "less than or equal to " + referenceDate;
			break;
	}	
}

function checkDate(date1, objDate2, format, operator){
	if ((objDate2!=null) ){
			dateFormat=format;
			formatChar = " "
			aFormat	= dateFormat.split(formatChar)
			if (aFormat.length<3)
			{
				formatChar = "/"
				aFormat	= dateFormat.split(formatChar)
				if (aFormat.length<3)
				{
					formatChar = "."
					aFormat	= dateFormat.split(formatChar)
					if (aFormat.length<3)
					{
						formatChar = "-"
						aFormat	= dateFormat.split(formatChar)
						if (aFormat.length<3)
						{
							formatChar=""
							return false;
						}
					}
				}
			}
			
			if ( formatChar	!= "" ){
				var dateSelected  = new Array(2);
				var monthSelected = new Array(2);
				var yearSelected  = new Array(2);
				var xData = new Array(2);
				xData[0] = date1;
				xData[1] = objDate2.value;
				for (var z=0;z<2;z++){
					aData = xData[z].split(formatChar);
					for	(i=0;i<3;i++){
						if ((aFormat[i]=="d") || (aFormat[i]=="dd")){
							dateSelected[z] = parseInt(aData[i], 10)
						}else if((aFormat[i]=="m") || (aFormat[i]=="mm")){
							monthSelected[z] =	parseInt(aData[i], 10) - 1
						}else if	(aFormat[i]=="yyyy"){
							yearSelected[z]= parseInt(aData[i], 10)
						}else if	(aFormat[i]=="mmm"){
							for	(j=0; j<12;	j++){
								if (aData[i]==monthName[j]){
									monthSelected[z]=j
								}
							}
						}
					}
				
				}
			}
		if (objDate2.value==""){
			return false;
		}	
		
		// Date validation
		var tgl1 = new Date;
		var tgl2 = new Date;
		tgl1 = stringToDate(yearSelected[0],monthSelected[0],(dateSelected[0]-1));
	    tgl2 = stringToDate(yearSelected[1],monthSelected[1],(dateSelected[1]-1));
		switch (operator) {
			case 1 :
				if (tgl2 < (tgl1 + 1)){
					return false;
				}
				break;
			case 2 :
				if ((tgl2 + 1) > tgl1){
					return false;
				}
				break;
			case 3 :
				if (tgl2 != tgl1){
					return false;
				}
				break;
			case 4 :
				if (tgl2 < tgl1){  
					return false;
				}
				break;
			case 5 :
				if (tgl2 > tgl1){
					return false;
				}
				break;
		}
	}
	return true;
}

function stringToDate(stryear, strmonth, strdate){
	var d = new Date;
	d.setFullYear(stryear);
	d.setDate(strdate);
	d.setMonth(strmonth);
	return d.valueOf();
}

function date2String(tgl,strFormat){
	var d=new Date();
	var xData=	new Array(2);
	var dateFormat=strFormat;
	var formatChar=" ";
	var aFormat	= dateFormat.split(formatChar)
	if (aFormat.length<3){
		formatChar = "/"
		aFormat	= dateFormat.split(formatChar)
		if (aFormat.length<3){
			formatChar = "."
			aFormat	= dateFormat.split(formatChar)
			if (aFormat.length<3){
				formatChar = "-"
				aFormat	= dateFormat.split(formatChar)
				if (aFormat.length<3){
					formatChar=""
				}
			}
		}
	}
	d=tgl;
	if ( formatChar!= "" ){
		for	(i=0;i<3;i++){
			if ((aFormat[i]=="d") || (aFormat[i]=="dd")){
				xData[i]=d.getDate();
			}else if((aFormat[i]=="m") || (aFormat[i]=="mm")){
				xData[i]=d.getMonth()+1;
			}else if (aFormat[i]=="yyyy"){
				xData[i]=d.getFullYear();
			}
		}
				
	}
	return (xData[0]+formatChar+xData[1]+ formatChar +xData[2]);	
}

function getNow(strFormat){
	var	today =	new	Date();
	return date2String(today,strFormat);
}



function checkFormatDate(obj,strFormat){
		var xData=new Array(2);
		var xHasil=new Array(2);
		xData[0]=strFormat;
		xData[1]=obj.value;
		var aFormat=new Array(2);
		var xFormat=new Array(2);
		var dateFormat;
		var formatChar;
		var xformatChar;
		for (var i=0;i<2;i++){
			dateFormat=xData[i];
			formatChar =  " ";
			aFormat= dateFormat.split(formatChar);
			if (aFormat.length<3){
				formatChar = "/"
				aFormat	= dateFormat.split(formatChar);
				if (aFormat.length<3){
					formatChar = "."
					aFormat	= dateFormat.split(formatChar);
					if (aFormat.length<3){
						formatChar = "-"
						aFormat= dateFormat.split(formatChar);
						if (aFormat.length<3){
							alert("Date format is not valid ("+strFormat+")");
							obj.value="";
							obj.focus();
							return;
						}
					}
				}
			}
			if(i==0){
				xFormat[0]= aFormat[0];
				xFormat[1]= aFormat[1];
				xFormat[2]= aFormat[2];
				xformatChar=formatChar;
			}
			
		}
		if ( formatChar	!= "" ){
			for	(i=0;i<3;i++){
				if ((xFormat[i]=="d") || (xFormat[i]=="dd")){
					if ((aFormat[i]>34) || (isNaN(parseInt(aFormat[i])/1))){
						alert("Date format is not valid ("+strFormat+") ");
						obj.value="";
						return;
					}
				}else if((xFormat[i]=="m") || (xFormat[i]=="mm")){
						if ((aFormat[i]>12) || (isNaN(parseInt(aFormat[i])/1))){
						alert("Date format is not valid ("+strFormat+")");
						obj.value="";
						return;
					}
				}else if(xFormat[i]=="yyyy"){
					if ((aFormat[i]<1900) || (isNaN(parseInt(aFormat[i])/1))){
						alert("Date format is not valid ("+strFormat+")");
						obj.value="";
						return;
					}
				}else if(xFormat[i]=="mmm"){
					var chkmth=false;
					for	(j=0; j<12;	j++){
						if (aFormat[i]==monthName[j]){
							chkmth=true;
						}
					}
					if(!chkmth){
						alert("Date format is not valid ("+strFormat+")");
						obj.value="";
						return;
					}
				}
			}
			if (formatChar!=xformatChar){
				obj.value=aFormat[0]+xformatChar+aFormat[1]+xformatChar+aFormat[2];
				obj.focus();
			}
		
		}
}

function lTrim(sString) {
	if(sString.length < 1) return sString;
	
	while (sString.substring(0,1) == ' '){
		sString = sString.substring(1, sString.length);
	}
	return sString;
}

function rTrim(sString){
	if(sString.length < 1) return sString;

	while (sString.substring(sString.length-1, sString.length) == ' '){
		sString = sString.substring(0,sString.length-1);
	}
	return sString;
}

function trim(sString){
	var _result;
	_result = lTrim(sString);
	_result = rTrim(_result);
	
	return _result;
}	

Array.prototype.inArray = function (value)
//Returns true if the passed value is found in the
//array. Returns false if it is not.
{
	var i;
	for (i=0; i < this.length; i++) {
		//use === to check for Matches. ie., identical (===),
		
			if (this[i] == value) {
				return true;
			}
		}
	
	return false;
};

function checkAllCheckboxes(checkboxName, referenceCheckbox) {
	var checkboxes = document.getElementsByName(checkboxName);
	$(checkboxes).attr('checked', referenceCheckbox.checked);
}

function moveOnMax(field,nextFieldID){
	if(field.value.length >= field.maxLength){
		document.getElementById(nextFieldID).focus();
	}
}

function populatePKs(tableName,first) {
	var maintenancePKs = "";
	$("#"+tableName).each(function(){
		$("tr.listRow").each(function(){
			tr=$(this);
			chkBox=tr.find("input:checkbox");
			if(chkBox.prop("checked")){
				if(first){
					maintenancePKs += "&amp;maintenancePKs=" + chkBox.val();
					first = false;
				}else{
					maintenancePKs += "&amp;maintenancePKs=" + chkBox.val();
				}
			}
		});
	});

    return maintenancePKs;
}

function selectAllCheckboxes(obj,tableName){
	$("#"+tableName).each(function(){
		$("tr.listRow").each(function(){
			var tr=$(this);
			var chkBox=tr.find("input:checkbox");
			if(obj.checked==true){
				if(chkBox.prop("disabled")==false){
					chkBox.prop("checked",true);
				}
			}else{
				chkBox.prop("checked",false);
			}
		});
  	});
}

function buildMenuTrees(menuTrees, rootNode, tree){
	for (var i = 0; i < menuTrees.length; i++) {
	    var counter = menuTrees[i];
	    if(counter.parentValue == "-1"){
	    	rootNode.addChildren({
	    		title: counter.name,
	    		tooltip: counter.name,
	    		isFolder: true,
	    		url: counter.path + '&skipDecorator',
	    		key: counter.value,
	    		icon: 'base.gif'
	    	});
	    }else{
	    	var parentNode = tree.getNodeByKey(counter.parentValue);
	    	parentNode.addChildren({
	    		title: counter.name,
	    		tooltip: counter.name,
	    		isFolder: false,
	    		url: counter.path + '&skipDecorator',
	    		key: counter.value
	    	});
	    	parentNode.folder = true;
	    }
	}
}

function buildDataTableWithCustomFilter(base, url, id, height, colDefs, detailFunc, detailParams, customAjaxOpts, customFilter) {
	var tableColumns = buildColumns(colDefs,false);
	buildDataTableFilter(base,id,colDefs,false);
	var ajaxOpt = getDefaultAjaxOpts(url,id);
	if(customAjaxOpts){
		ajaxOpt = customAjaxOpts;
	}
	if(customFilter) {
		ajaxOpt = buildCustomAjaxOpts(url, id, customFilter);
	}
    var table = $('#'+id).DataTable( {
    	ordering: false,
    	sDom: '<"#datatable-search-filter"><"top">rt<"bottom"><"clear">',
    	scrollY: height,
    	processing: true,
		serverSide: true,
    	ajax: ajaxOpt,
    	columns: tableColumns,
    	language: {
            infoEmpty: "No Data Found..."
        },
        oLanguage : {
        	sSearch : "Search Filter"
        } ,
        fnServerParams : function(aoData) {
        	$('#'+id+' .datatable-custom-filter').each(function(){
        		aoData.push({name: $(this).attr('name'), value: $(this).val()});
        	});
        }
    });
    if(detailFunc){
    	$('#'+id+' tbody').on( 'click', 'tr', function () {
        	var params = [];
        	var detailData = table.row( this ).data();
        	for(var j=0; j<detailParams.length;j++){
        		params[detailParams[j]] = detailData[detailParams[j]]; 
        	}
            window[detailFunc](params);
        } );	
    }
    if($('#selectAll-'+id)){
    	$('#selectAll-'+id).on('click', function(){
    		var rows = table.rows({ 'search': 'applied' }).nodes();
    		$('input[type="checkbox"]', rows).not(':disabled').prop('checked', this.checked);
    	});
    	$('#'+id+' tbody').on('change', 'input[type="checkbox"]', function(){
    		var totalCb = $('#'+id+' tbody input[type="checkbox"]').not(':disabled').length;
    		var totalCk = $('#'+id+' tbody input[type="checkbox"]').not(':disabled').totalChecked();
			if(totalCk==0){
				$('#selectAll-'+id).prop('indeterminate',false);
				$('#selectAll-'+id).prop('checked',false);
			}else if(totalCk==totalCb){
				$('#selectAll-'+id).prop('indeterminate',false);
				$('#selectAll-'+id).prop('checked',true);
			}else{
				$('#selectAll-'+id).prop('indeterminate',true);
				$('#selectAll-'+id).prop('checked',true);
			}
    	});
    }
}

function buildDataTable(base, url, id, height, colDefs, detailFunc, detailParams, customAjaxOpts){
	var tableColumns = buildColumns(colDefs,false);
	buildDataTableFilter(base,id,colDefs,false);
	var ajaxOpt = getDefaultAjaxOpts(url,id);
	if(customAjaxOpts){
		ajaxOpt = customAjaxOpts;
	}
    var table = $('#'+id).DataTable( {
    	ordering: false,
    	sDom: '<"#datatable-search-filter"f><"top">rt<"bottom"><"clear">',
    	scrollY: height,
    	processing: true,
		serverSide: true,
    	ajax: ajaxOpt,
    	columns: tableColumns,
    	language: {
            infoEmpty: "No Data Found..."
        },
        oLanguage : {
        	sSearch : "Search Filter"
        } 
    });
    if(detailFunc){
    	$('#'+id+' tbody').on( 'click', 'tr', function () {
        	var params = [];
        	var detailData = table.row( this ).data();
        	for(var j=0; j<detailParams.length;j++){
        		params[detailParams[j]] = detailData[detailParams[j]]; 
        	}
            window[detailFunc](params);
        } );	
    }
    if($('#selectAll-'+id)){
    	$('#selectAll-'+id).on('click', function(){
    		var rows = table.rows({ 'search': 'applied' }).nodes();
    		$('input[type="checkbox"]', rows).not(':disabled').prop('checked', this.checked);
    	});
    	$('#'+id+' tbody').on('change', 'input[type="checkbox"]', function(){
    		var totalCb = $('#'+id+' tbody input[type="checkbox"]').not(':disabled').length;
    		var totalCk = $('#'+id+' tbody input[type="checkbox"]').not(':disabled').totalChecked();
			if(totalCk==0){
				$('#selectAll-'+id).prop('indeterminate',false);
				$('#selectAll-'+id).prop('checked',false);
			}else if(totalCk==totalCb){
				$('#selectAll-'+id).prop('indeterminate',false);
				$('#selectAll-'+id).prop('checked',true);
			}else{
				$('#selectAll-'+id).prop('indeterminate',true);
				$('#selectAll-'+id).prop('checked',true);
			}
    	});
    }
}

function buildDataTableWithoutFilter(base, url, id, height, colDefs, detailFunc, detailParams, customAjaxOpts){
	var tableColumns = buildColumns(colDefs,false);
	buildDataTableFilter(base,id,colDefs,false);
	var ajaxOpt = getDefaultAjaxOpts(url,id);
	if(customAjaxOpts){
		ajaxOpt = customAjaxOpts;
	}
    var table = $('#'+id).DataTable( {
    	ordering: false,
    	sDom: '<"#datatable-search-filter"f><"top">rt<"bottom"><"clear">',
    	scrollY: height,
    	processing: true,
		serverSide: true,
		bFilter: false,
    	ajax: ajaxOpt,
    	columns: tableColumns,
    	language: {
            infoEmpty: "No Data Found..."
        },
        oLanguage : {
        	sSearch : "Search Filter"
        } 
    });
    if(detailFunc){
    	$('#'+id+' tbody').on( 'click', 'tr', function () {
        	var params = [];
        	var detailData = table.row( this ).data();
        	for(var j=0; j<detailParams.length;j++){
        		params[detailParams[j]] = detailData[detailParams[j]]; 
        	}
            window[detailFunc](params);
        } );	
    }
    if($('#selectAll-'+id)){
    	$('#selectAll-'+id).on('click', function(){
    		var rows = table.rows({ 'search': 'applied' }).nodes();
    		$('input[type="checkbox"]', rows).not(':disabled').prop('checked', this.checked);
    	});
    	$('#'+id+' tbody').on('change', 'input[type="checkbox"]', function(){
    		var totalCb = $('#'+id+' tbody input[type="checkbox"]').not(':disabled').length;
    		var totalCk = $('#'+id+' tbody input[type="checkbox"]').not(':disabled').totalChecked();
			if(totalCk==0){
				$('#selectAll-'+id).prop('indeterminate',false);
				$('#selectAll-'+id).prop('checked',false);
			}else if(totalCk==totalCb){
				$('#selectAll-'+id).prop('indeterminate',false);
				$('#selectAll-'+id).prop('checked',true);
			}else{
				$('#selectAll-'+id).prop('indeterminate',true);
				$('#selectAll-'+id).prop('checked',true);
			}
    	});
    }
}

function buildDataTableFilter(base,id,colDefs,disabled){
	var wonSearch = 'defaultDataTableSearch(\''+id+'\')';
	var wonReset = 'defaultDataTableReset(\''+id+'\')';
	var colId = 0;
	var dateInputs = [];
	$('#'+id+' thead td').each( function () {
        var title = $('#'+id+' thead th').eq( $(this).index() ).text();
        var colFilId = 'colFil_'+id+'_'+colId;
        var disabledStr = '';
        if(disabled){
        	disabledStr = 'disabled="disabled"';
        }
        var maxLengthStr = '';
        if(colDefs[colId].maxlength){
        	maxLengthStr = 'maxlength="'+colDefs[colId].maxlength+'"'
        }
        var inputText = '<input class="columnFilter" id="'+colFilId+'" type="text" placeholder="Search '+title+'" '+disabledStr+' '+maxLengthStr+'/>';
        var inputElement = inputText;
        if(colDefs[colId].ftype=='select'){
        	inputElement = $('<select class="columnFilter" id="'+colFilId+'" '+disabledStr+'></select>');
        	if(colDefs[colId].values){
        		for(i=0;i<colDefs[colId].values.length;i++){
        			var vals = colDefs[colId].values[i];
        			var opt = '<option value="'+vals.value+'">';
        			if(colDefs[colId].fpattern){
        				opt=opt+formatLabelOpt(vals,colDefs[colId].fpattern);
        			}else{
        				opt=opt+(vals.label?vals.label:vals.value);
        			}
        			opt=opt+'</option>';
        			inputElement.append(opt);
        		}
        	}
        }else if(colDefs[colId].ftype=='date'){
        	var inputElement = '<input readonly="true" class="columnFilter" id="'+colFilId+'" type="text" placeholder="Search '+title+'" />';
        	dateInputs.push(colFilId);
        }
        if($(this).hasClass('searcher')){
        	if(disabled){
        		var imgSearch = '<img src="'+base+'images/search24.gif" class="toolBarImageButton3"/>';
            	var imgReset = '<img src="'+base+'images/reset24.gif" class="toolBarImageButton3"/>';
            	$(this).html(inputElement+imgSearch+imgReset);
        	}else{
        		var imgSearch = '<img src="'+base+'images/search24.gif" class="toolBarImageButton3" onmouseover="this.className=\'toolBarImageHover3\'" onmouseout="this.className=\'toolBarImageButton3\'" onclick="'+wonSearch+'"/>';
            	var imgReset = '<img src="'+base+'images/reset24.gif" class="toolBarImageButton3" onmouseover="this.className=\'toolBarImageHover3\'" onmouseout="this.className=\'toolBarImageButton3\'" onclick="'+wonReset+'"/>';
            	var emptyHidden = '<input type="hidden" id="emptyList" value="true"/>';
            	$(this).html(inputElement+imgSearch+imgReset+emptyHidden);
        	}
        }else{
        	$(this).html(inputElement);
        }
        colId = colId + 1;
    } );
	for(var dd=0;dd<dateInputs.length;dd++){
		$('#'+dateInputs[dd]).datepicker({dateFormat: 'dd-M-yy',showOn:'both',changeYear: true,changeMonth:true,buttonImage: base+'images/calendaricon.gif', buttonImageOnly: true, yearRange: '1900:2100'});
		if(disabled){
			$('#'+dateInputs[dd]).datepicker("option","disabled",true);
		}
	}
}

function buildColumns(colDefs,list){
	var tableResult = [];
	for(i=0;i<colDefs.length;i++){
		var tabCol = {};
		tabCol.data = colDefs[i].name;
		if(colDefs[i].type=='date'){
			tabCol.type = colDefs[i].type;
			tabCol.render = function(data){
				if(data){
					var date = $.datepicker.formatDate('dd-MM-yy', new Date(data));
					return date;					
				}else{
					return '';
				}
			}
		}else if(colDefs[i].type=='timestamp'){
			tabCol.type = colDefs[i].type;
			tabCol.render = function(data){
				if(data){
					var date = $.datepicker.formatDate("dd-MM-yy", new Date(data));
					var dateTemp = new Date(data);
					date += ' ' + dateTemp.getHours() + ':' + dateTemp.getMinutes() + ":" + dateTemp.getSeconds();
					return date;
				}else{
					return '';
				}
			}
		}else if(colDefs[i].type=='link'){
			var colDefsTemp = colDefs[i];
			tabCol.render = function(data, type, row, meta){
				var paramRender = '';
				console.log(colDefsTemp);
				if(colDefsTemp.param){
					for(var index = 0;index<colDefsTemp.param.length;index++){
						if(index!=0){
							paramRender += ',';
						}
						if(colDefsTemp.param[index].indexOf(".") > -1){
							var resolverTemp = colDefsTemp;
							var arrParam = colDefsTemp.param[index].split(".");
							for(var arrIdx=0;arrIdx<arrParam.length;arrIdx++){
								if(arrIdx==0){
									resolverTemp = row[arrParam[arrIdx]];
								}else{
									resolverTemp = resolverTemp[arrParam[arrIdx]];
								}
							}
							paramRender += '\'' + resolverTemp + '\'';
						}else{							
							paramRender += '\'' + row[colDefsTemp.param[index]] + '\'';
						}
					}	
				}
				return '<a onclick="' + colDefsTemp.onclick + '(' + paramRender + ')">'+data+'</a>';
			}
		}else if(colDefs[i].type=='link-default'){
			var colDefsTemp = colDefs[i];
			tabCol.render = function(data, type, row, meta){
				var paramRender = '';
				console.log(colDefsTemp);
				if(colDefsTemp.param){
					for(var index = 0;index<colDefsTemp.param.length;index++){
						if(index!=0){
							paramRender += ',';
						}
						if(colDefsTemp.param[index].indexOf(".") > -1){
							var resolverTemp = colDefsTemp;
							var arrParam = colDefsTemp.param[index].split(".");
							for(var arrIdx=0;arrIdx<arrParam.length;arrIdx++){
								if(arrIdx==0){
									resolverTemp = row[arrParam[arrIdx]];
								}else{
									resolverTemp = resolverTemp[arrParam[arrIdx]];
								}
							}
							paramRender += '\'' + resolverTemp + '\'';
						}else{							
							paramRender += '\'' + row[colDefsTemp.param[index]] + '\'';
						}
					}	
				}
				return '<a onclick="' + colDefsTemp.onclick + '(' + paramRender + ')" class="normal-link">'+'See More'+'</a>';
			}
		}else if(colDefs[i].type=='linker'){
			tabCol.render = function(data){
				var clickable = data;
				if(clickable==undefined){
					clickable = '_';
				}
				return '<a href="#" class="linker">'+clickable+'</a>';
			}
		}else if(colDefs[i].type=='no'){
			tabCol.render = function(data,type,row,meta){
				return meta.row+1;
			}
		} else if( colDefs[i].type == 'deleteAndEdit' ) {
			var colDefsTemp = colDefs[i];
			tabCol.render = function(data, type, row, meta){
				return '<a href="#" onclick="showDetail(' + renderParam(colDefsTemp, row) + ')" class="btn btn-primary btn-xs" data-toggle="tooltip" title="" data-original-title="Edit" style="margin-right:5px;"><i class="fa fa-edit"></i></a>' +
					 '<a href="#" onclick="deleteItem(' + renderParam(colDefsTemp, row) + ')" class="btn btn-primary btn-xs" data-toggle="tooltip" title="" data-original-title="Delete"><i class="fa fa-trash"></i></a>';
			}
		} else if( colDefs[i].type == 'delete' ) {
			var colDefsTemp = colDefs[i];
			tabCol.render = function(data, type, row, meta){
				return '<a href="#" onclick="deleteItem(' + renderParam(colDefsTemp, row) + ')" class="btn btn-primary btn-xs" data-toggle="tooltip" title="" data-original-title="Delete"><i class="fa fa-trash"></i></a>';
			}
		} else if( colDefs[i].type == 'detail' ) {
			var colDefsTemp = colDefs[i];
			var paramD = colDefs[i].param;
			var paramDRowId = "row."+paramD[0];
			var paramDRowLabel = "row."+paramD[1];
			tabCol.render = function(data, type, row, meta){	
				return '<a href="#" onclick="showDetail(' + eval(paramDRowId) + ')" class="btn btn-primary btn-xs" data-toggle="tooltip" title="Detail">' + eval(paramDRowLabel) + '</a>';
			}
		} else if( colDefs[i].type == 'edit' ) {
			var colDefsTemp = colDefs[i];
			tabCol.render = function(data, type, row, meta){
				return '<a href="#" onclick="showDetail(' + renderParam(colDefsTemp, row) + ')" class="btn btn-primary btn-xs" data-toggle="tooltip" title="" data-original-title="Edit"><i class="fa fa-edit"></i></a>';
			}
		} else if( colDefs[i].type == 'input' ) {
			var colDefsTemp = colDefs[i];
			var paramI = colDefs[i].param;
			var valueI = "row."+paramI[0];
			var statusI = "row."+paramI[1];
			tabCol.render = function(data, type, row, meta){
				if(eval(statusI)==1){
					return '<input type="text" class="input-data" value="'+ eval(valueI) +'" />';
				}else{
					return eval(valueI);
				}
			}
		} else if( colDefs[i].type == 'select' ) {
			var colDefsTemp = colDefs[i];
			var paramSL = colDefs[i].param;
			var valueSL = "row."+paramSL[0];
			var statusSL = "row."+paramSL[1];
			var idSelectSL = paramSL[2];
			var pkLookupSL = "row."+paramSL[3];
			tabCol.render = function(data, type, row, meta){
				if(eval(statusSL)==1){
					var objSL = $('#'+idSelectSL+' select');
					var html = "<select class='"+objSL.attr('class')+"'>";
					for(var i=0; i<objSL.children().length;i++){
						var valueSLOption = $(objSL.children().get(i)).attr('value');
						html += "<option value='"+valueSLOption+"' ";
						if(valueSLOption==eval(pkLookupSL)){
							html += "selected='selected'>";
						}else{
							html += ">";
						}
						html +=$(objSL.children().get(i)).text()+"</option>";	
					}
					html+="</select>";
					return html;
				}else{
					return eval(valueSL);
				}
			}
		} else if( colDefs[i].type == 'submit' ) {
			var colDefsTemp = colDefs[i];
			var paramS = colDefs[i].param;
			var pkS = "row."+paramS[0];
			var statusS = "row."+paramS[1];
			tabCol.render = function(data, type, row, meta){
				if(eval(statusS)==1){
					return '<a href="#" onclick="submitRecord(' + eval(pkS) + ',this)" class="btn btn-primary btn-xs" data-toggle="tooltip"><i class="fa fa-hand-o-right"></i></a>';
				}else{
					return '<i class="fa fa-check-square-o"></i>';
				}
			}
		}else if(colDefs[i].type=='checkbox'){
			tabCol.render = function(data, type, row, meta){
				var disabledCheckbox = false;
				var colDefsTemp = colDefs[meta.col];
				var idInput = colDefsTemp.paramId == undefined ? "" : colDefsTemp.paramId;
				var renderedValue = '<input type="checkbox" class="minimal" name="select" id="'+idInput+'" value="'+data+'"/>';
				
				if(disabledCheckbox){
						renderedValue = '<input type="checkbox" class="minimal" name="select" id="'+idInput+'" value="'+data+'" disabled="disabled"/>';
				}
				if(colDefsTemp.dataType && colDefsTemp.dataType=='boolean'){
					if(data){
						renderedValue = '<input type="checkbox" class="minimal" name="select" id="'+idInput+'" value="'+data+'" checked="checked"/>';
					}
				}
				
				return renderedValue;
			}
			tabCol.orderable = false;
			tabCol.className="dt-center";
		}
		
		tabCol.defaultContent = '';
		tableResult[i] = tabCol;
	}
	if(list){
		if(list==true){			
			tableResult[i-1].width='1%';
		}
	}
	return tableResult;
}

function renderParam (colDefsTemp, row) {
	var paramRender = '';
	if(colDefsTemp.param){
		for(var index = 0;index<colDefsTemp.param.length;index++){
			if(index!=0){
				paramRender += ',';
			}
			if(colDefsTemp.param[index].indexOf(".") > -1){
				var resolverTemp = colDefsTemp;
				var arrParam = colDefsTemp.param[index].split(".");
				for(var arrIdx=0;arrIdx<arrParam.length;arrIdx++){
					if(arrIdx==0){
						resolverTemp = row[arrParam[arrIdx]];
					}else{
						resolverTemp = resolverTemp[arrParam[arrIdx]];
					}
				}
				paramRender += '\'' + resolverTemp + '\'';
			}else{
				paramRender += '\'' + row[colDefsTemp.param[index]] + '\'';
			}
		}	
	}
	return paramRender;
}

function buildCustomAjaxOpts(url, id, customFilter) {
	var ajaxOpt = {
			url: url,
			type: 'GET',
			dataSrc: function(json){
				$('#txtPage_'+id).val(json.currentPage);
				$('#_txtOldPage_'+id).val(json.currentPage);
				$('#noOfPage').html(json.noOfPage);
				if(json.currentPage>1){				
					$('#btnPrev').css('display','');
				}else{
					$('#btnPrev').css('display','none');
				}
				if(json.currentPage<json.noOfPage){
					$('#btnNext').css('display','');
				}else{
					$('#btnNext').css('display','none');
				}
				return json.result;
			},
			data: customFilter,
			error: function(message) {
				console.log('message : ' + message);
			}
		};
	return ajaxOpt;
}

function getDefaultAjaxOpts(url,id){
	var ajaxOpt = {
			url: url,
			type: 'GET',
			dataSrc: function(json){
				$('#txtPage_'+id).val(json.currentPage);
				$('#_txtOldPage_'+id).val(json.currentPage);
				$('#noOfPage').html(json.noOfPage);
				if(json.currentPage>1){				
					$('#btnPrev').css('display','');
				}else{
					$('#btnPrev').css('display','none');
				}
				if(json.currentPage<json.noOfPage){
					$('#btnNext').css('display','');
				}else{
					$('#btnNext').css('display','none');
				}
				return json.result;
			},
			data: function(d){
				for (var i = 0; i < d.columns.length; i++) {
			        column = d.columns[i];
			        column.searchRegex = column.search.regex;
			        column.searchValue = column.search.value;
			        delete(column.search);
			    }
				d.emptyList = $('#emptyList').val();
				d.page = $('#txtPage_'+id).val();
			},
			error: function(message) {
				console.log('message : ' + message);
			}
		};
	return ajaxOpt;
}

function reloadDataTableSearch(id){
	var table = $('#'+id).DataTable();
	table.columns().every( function () {
		$('#colFil_'+id+'_'+this.index()).val(this.search());
    });
	$('#emptyList').val(false);
	table.ajax.reload();
}

function reloadDataTableSearchCustom(id) {
	reloadDataTableSearch(id);
}

function setCompanyFromSession(id) {	
	$('#'+id).val($('#session-company-selected').val());
}

function initNumeric (classComponent) {
	var classTarget = "numeric";
	if(classComponent) {
		classTarget = classComponent;
	}
	$("."+classTarget).numeric();
}
function showScrollForm(id,addVal){
	$('#content').css('min-height',parseInt($('#'+id).height())+addVal);
}
function requiredInit(){
	$('.required').each(function(){
		$(this).append('<span class="required-icon">*</span>')
	});
}

var toolbarOptions = [
  ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
  ['blockquote', 'code-block'],

  [{ 'header': 1 }, { 'header': 2 }],               // custom button values
  [{ 'list': 'ordered'}, { 'list': 'bullet' }],
  [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
  [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
  [{ 'direction': 'rtl' }],                         // text direction

  [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
  [{ 'header': [1, 2, 3, 4, 5, 6, false] }],

  [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
  [{ 'font': [] }],
  [{ 'align': [] }],
  
  ['link', 'image'],
  
  ['clean']  
];

function initDatePicker(datePickerClass, dateFormat) {
	$('.'+datePickerClass).datepicker({
	    autoclose: true,
	    todayHighlight: true,
	    changeMonth: true,
	    changeYear: true,
		format: dateFormat,
		yearRange: "1900:2100",
		onSelect: function(dateText, datePicker) {
			$(this).attr('value', dateText);
	    }
    });
}

function initTimePicker(timePickerClass, timeFormat) {
	$('.'+timePickerClass).timepicker({
        showDuration: true,
        hours: {
    		starts: 0,
    		ends: 23
    	},
        timeFormat: timeFormat
    });
}

function uploadFileTextEditor(image, sURL, targetId) {
	var data = new FormData();
	data.append('file', image);
	var xhr = new XMLHttpRequest();
	xhr.open('POST', sURL, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
		  var response = JSON.parse(xhr.responseText);
		  if (response) {
			  $("#"+targetId).val(response);
		  } else {
			  return 'error';
		  }
		}
	}
	xhr.send(data);
}

function initTextEditor(editorArea, editorParent, uploadURL) {
	$(editorArea).each(function(){		
		var hiddenInput = document.createElement("input");
			hiddenInput.className = "hidden-file-browser";
			hiddenInput.type = "file";
			hiddenInput.name = "image";
			hiddenInput.style.display = "none";
		$(this).parent().append(hiddenInput);
	});
	
	$('.hidden-file-browser').change(function(){
		var thisObj = $(this)[0];
		var targetId = $(this).attr('target-id');
		uploadFileTextEditor(thisObj.files[0], uploadURL, targetId);
	});
	
	tinymce.init({
    	selector: editorArea,
    	plugins: [
    	    'advlist autolink lists link image charmap print preview hr anchor pagebreak',
    	    'searchreplace wordcount visualblocks visualchars code fullscreen',
    	    'insertdatetime media nonbreaking save table contextmenu directionality',
    	    'emoticons template paste textcolor colorpicker textpattern imagetools codesample toc'
    	],
    	toolbar1: 'undo redo | insert | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
    	toolbar2: 'imageprint preview media | forecolor backcolor emoticons | codesample',
    	file_browser_callback : function(field_name, url, type, win) {
			var hiddenInput = $("#"+tinyMCE.activeEditor.id).parent().find('.hidden-file-browser');
    		hiddenInput.attr("target-id", field_name);
			hiddenInput.click();
        },
        height:"200"
  	});
}

function initTextEditorWithoutP(editorArea, editorParent, uploadURL) {
	$(editorArea).each(function(){		
		var hiddenInput = document.createElement("input");
			hiddenInput.className = "hidden-file-browser";
			hiddenInput.type = "file";
			hiddenInput.name = "image";
			hiddenInput.style.display = "none";
		$(this).parent().append(hiddenInput);
	});
	
	$('.hidden-file-browser').change(function(){
		var thisObj = $(this)[0];
		var targetId = $(this).attr('target-id');
		uploadFileTextEditor(thisObj.files[0], uploadURL, targetId);
	});
	
	tinymce.init({
		forced_root_block : "", 
	    force_br_newlines : true,
	    force_p_newlines : false,
    	selector: editorArea,
    	plugins: [
    	    'advlist autolink lists link image charmap print preview hr anchor pagebreak',
    	    'searchreplace wordcount visualblocks visualchars code fullscreen',
    	    'insertdatetime media nonbreaking save table contextmenu directionality',
    	    'emoticons template paste textcolor colorpicker textpattern imagetools codesample toc'
    	],
    	toolbar1: 'undo redo | insert | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
    	toolbar2: 'imageprint preview media | forecolor backcolor emoticons | codesample',
    	file_browser_callback : function(field_name, url, type, win) {
			var hiddenInput = $("#"+tinyMCE.activeEditor.id).parent().find('.hidden-file-browser');
    		hiddenInput.attr("target-id", field_name);
			hiddenInput.click();
        },
        height:"200"
  	});
}

function bootBoxSuccessSave(customMessage, customCallback) {
	bootbox.alert({
        title: "Success",
        message: customMessage,
        callback: customCallback
    });
}

function bootBoxError(error, customCallback) {
	if(!customCallback) {
		customCallback = function() {};
	}
	bootbox.alert({
        title: "Error",
        message: error,
        callback: customCallback
    });
}

function bootBoxConfirmation(customCallback) {
	bootbox.confirm({
		message: "Are you sure you want to delete this record?",
	    buttons: {
	        confirm: {
	            label: 'Yes',
	            className: 'btn-success'
	        },
	        cancel: {
	            label: 'No',
	            className: 'btn-danger'
	        }
	    },
	    callback: customCallback
	});
}

demo = {
    initPickColor: function(){
        $('.pick-class-label').click(function(){
            var new_class = $(this).attr('new-class');  
            var old_class = $('#display-buttons').attr('data-class');
            var display_div = $('#display-buttons');
            if(display_div.length) {
            var display_buttons = display_div.find('.btn');
            display_buttons.removeClass(old_class);
            display_buttons.addClass(new_class);
            display_div.attr('data-class', new_class);
            }
        });
    },
    
    initChartist: function(){    
        
        var dataSales = {
          labels: ['9:00AM', '12:00AM', '3:00PM', '6:00PM', '9:00PM', '12:00PM', '3:00AM', '6:00AM'],
          series: [
             [287, 385, 490, 492, 554, 586, 698, 695, 752, 788, 846, 944],
            [67, 152, 143, 240, 287, 335, 435, 437, 539, 542, 544, 647],
            [23, 113, 67, 108, 190, 239, 307, 308, 439, 410, 410, 509]
          ]
        };
        
        var optionsSales = {
          lineSmooth: false,
          low: 0,
          high: 800,
          showArea: true,
          height: "245px",
          axisX: {
            showGrid: false,
          },
          lineSmooth: Chartist.Interpolation.simple({
            divisor: 3
          }),
          showLine: false,
          showPoint: false,
        };
        
        var responsiveSales = [
          ['screen and (max-width: 640px)', {
            axisX: {
              labelInterpolationFnc: function (value) {
                return value[0];
              }
            }
          }]
        ];
    
        Chartist.Line('#chartHours', dataSales, optionsSales, responsiveSales);
        
    
        var data = {
          labels: ['Jan', 'Feb', 'Mar', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
          series: [
            [542, 443, 320, 780, 553, 453, 326, 434, 568, 610, 756, 895],
            [412, 243, 280, 580, 453, 353, 300, 364, 368, 410, 636, 695]
          ]
        };
        
        var options = {
            seriesBarDistance: 10,
            axisX: {
                showGrid: false
            },
            height: "245px"
        };
        
        var responsiveOptions = [
          ['screen and (max-width: 640px)', {
            seriesBarDistance: 5,
            axisX: {
              labelInterpolationFnc: function (value) {
                return value[0];
              }
            }
          }]
        ];
        
        Chartist.Bar('#chartActivity', data, options, responsiveOptions);
    
        var dataPreferences = {
            series: [
                [25, 30, 20, 25]
            ]
        };
        
        var optionsPreferences = {
            donut: true,
            donutWidth: 40,
            startAngle: 0,
            total: 100,
            showLabel: false,
            axisX: {
                showGrid: false
            }
        };
    
        Chartist.Pie('#chartPreferences', dataPreferences, optionsPreferences);
        
        Chartist.Pie('#chartPreferences', {
          labels: ['62%','32%','6%'],
          series: [62, 32, 6]
        });   
    },
    
    
};

(function( $ ) {
    $.fn.totalChecked = function() {
        var total = 0;
    	$(this).each(function(){ 
    		var checked = $(this).prop('checked');
			if (typeof checked !== typeof undefined && checked !== false) {
				total = parseInt(total) + 1;
			}
    	});
        
        return total;
    };
})( jQuery );