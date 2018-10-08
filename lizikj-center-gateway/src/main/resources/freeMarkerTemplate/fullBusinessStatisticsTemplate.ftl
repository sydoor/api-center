<?xml version="1.0"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:o="urn:schemas-microsoft-com:office:office"
 xmlns:x="urn:schemas-microsoft-com:office:excel"
 xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:html="http://www.w3.org/TR/REC-html40">
 <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
  <Author>USER</Author>
  <LastAuthor>ASUS</LastAuthor>
  <Created>2018-07-12T02:20:35Z</Created>
  <LastSaved>2018-08-08T02:44:48Z</LastSaved>
  <Company>CHINA</Company>
  <Version>14.00</Version>
 </DocumentProperties>
 <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
  <AllowPNG/>
 </OfficeDocumentSettings>
 <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
  <WindowHeight>11880</WindowHeight>
  <WindowWidth>20475</WindowWidth>
  <WindowTopX>480</WindowTopX>
  <WindowTopY>30</WindowTopY>
  <ProtectStructure>False</ProtectStructure>
  <ProtectWindows>False</ProtectWindows>
 </ExcelWorkbook>
 <Styles>
  <Style ss:ID="Default" ss:Name="Normal">
   <Alignment ss:Vertical="Center"/>
   <Borders/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="12"/>
   <Interior/>
   <NumberFormat/>
   <Protection/>
  </Style>
  <Style ss:ID="s21">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="9" ss:Color="#000000"/>
  </Style>
  <Style ss:ID="s22">
   <Alignment ss:Horizontal="Left" ss:Vertical="Center"/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="11" ss:Color="#000000"/>
   <NumberFormat ss:Format="@"/>
  </Style>
  <Style ss:ID="s23">
   <Alignment ss:Horizontal="Right" ss:Vertical="Center"/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="11" ss:Color="#000000"/>
   <NumberFormat ss:Format="#,##0.00##"/>
  </Style>
 </Styles>
 <Worksheet ss:Name="Sheet1">
  <Table ss:ExpandedColumnCount="3" ss:ExpandedRowCount="1000" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="14.25">
   <Column ss:AutoFitWidth="0" ss:Width="207.75"/>
   <Column ss:AutoFitWidth="0" ss:Width="107.25"/>
   <Row>
    <Cell ss:StyleID="s21"><Data ss:Type="String">项目</Data></Cell>
    <Cell ss:StyleID="s21"><Data ss:Type="String">数值</Data></Cell>
    <Cell ss:StyleID="s21"><Data ss:Type="String">单位</Data></Cell>
   </Row>
   <#if list?? && (list?size>0)>
   	<#list list as item>
   	   <Row>
	    <Cell ss:StyleID="s22"><Data ss:Type="String"><#if item.name??>${item.name}</#if></Data></Cell>
	    <Cell ss:StyleID="s23">
	    	<#if item.amount??>
	    		<#if item.isAmount == true><Data ss:Type="String">${item.amount/100}</Data>
	    		<#else><Data ss:Type="String">${item.amount}</Data>
	    		</#if>
	    	</#if>
	    </Cell>
	    <Cell ss:StyleID="s23"><Data ss:Type="String"><#if item.unit??>${item.unit}</#if></Data></Cell>
	   </Row>
	   <#if item.list?? && (item.list?size>0) && item.hasChild==true>
	   	<#list item.list as childItem>
	   	   <Row>
		    <Cell ss:StyleID="s22"><Data ss:Type="String"><#if childItem.name??> ${childItem.name}</#if></Data></Cell>
		    <Cell ss:StyleID="s23">
		    	<#if childItem.amount??>
		    		<#if childItem.isAmount == true><Data ss:Type="String">${childItem.amount/100}</Data>
		    		<#else><Data ss:Type="String">${childItem.amount}</Data>
		    		</#if>
		    	</#if>
		    </Cell>
		    <Cell ss:StyleID="s23"><Data ss:Type="String"><#if childItem.unit??>${childItem.unit}</#if></Data></Cell>
		   </Row>
		   <#if childItem.list?? && (childItem.list?size>0) && childItem.hasChild==true>
		   	<#list childItem.list as lastItem>
		   	   <Row>
			    <Cell ss:StyleID="s22"><Data ss:Type="String"><#if lastItem.name??>   ${lastItem.name}</#if></Data></Cell>
			    <Cell ss:StyleID="s23">
			    	<#if lastItem.amount??>
			    		<#if lastItem.isAmount == true><Data ss:Type="String">${lastItem.amount/100}</Data>
			    		<#else><Data ss:Type="String">${lastItem.amount}</Data>
			    		</#if>
			    	</#if>
			    </Cell>
			    <Cell ss:StyleID="s23"><Data ss:Type="String"><#if lastItem.unit??>${lastItem.unit}</#if></Data></Cell>
			   </Row>
		   	</#list>
		   </#if>
	   	</#list>
	   </#if>
   	</#list>
   </#if>
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <Print>
    <ValidPrinterInfo/>
    <PaperSizeIndex>9</PaperSizeIndex>
    <HorizontalResolution>600</HorizontalResolution>
    <VerticalResolution>600</VerticalResolution>
   </Print>
   <Selected/>
   <TopRowVisible>0</TopRowVisible>
   <Panes>
    <Pane>
     <Number>3</Number>
    </Pane>
   </Panes>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
 <Worksheet ss:Name="Sheet2">
  <Table ss:ExpandedColumnCount="1" ss:ExpandedRowCount="1" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="14.25">
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
 <Worksheet ss:Name="Sheet3">
  <Table ss:ExpandedColumnCount="1" ss:ExpandedRowCount="1" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="14.25">
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
</Workbook>
