<?xml version="1.0"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:o="urn:schemas-microsoft-com:office:office"
 xmlns:x="urn:schemas-microsoft-com:office:excel"
 xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:html="http://www.w3.org/TR/REC-html40">
 <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
  <Author>Microsoft Office 用户</Author>
  <LastAuthor>ASUS</LastAuthor>
  <Created>2018-07-11T03:49:32Z</Created>
  <LastSaved>2018-07-19T09:09:10Z</LastSaved>
  <Version>14.00</Version>
 </DocumentProperties>
 <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
  <AllowPNG/>
 </OfficeDocumentSettings>
 <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
  <WindowHeight>15540</WindowHeight>
  <WindowWidth>24765</WindowWidth>
  <WindowTopX>840</WindowTopX>
  <WindowTopY>465</WindowTopY>
  <TabRatio>500</TabRatio>
  <ProtectStructure>False</ProtectStructure>
  <ProtectWindows>False</ProtectWindows>
 </ExcelWorkbook>
 <Styles>
  <Style ss:ID="Default" ss:Name="Normal">
   <Alignment ss:Vertical="Bottom"/>
   <Borders/>
   <Font ss:FontName="DengXian" x:CharSet="134" ss:Size="12" ss:Color="#000000"/>
   <Interior/>
   <NumberFormat/>
   <Protection/>
  </Style>
  <Style ss:ID="s16">
   <NumberFormat ss:Format="General Date"/>
  </Style>
  <Style ss:ID="s17">
   <Alignment ss:Horizontal="Left" ss:Vertical="Center"/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="9" ss:Color="#000000"/>
   <NumberFormat ss:Format="@"/>
  </Style>
  <Style ss:ID="s18">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="9" ss:Color="#000000"/>
   <NumberFormat ss:Format="@"/>
  </Style>
  <Style ss:ID="s19">
   <Alignment ss:Horizontal="Right" ss:Vertical="Center"/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="9" ss:Color="#000000"/>
   <NumberFormat ss:Format="#,##0.00##"/>
  </Style>
  <Style ss:ID="s23">
   <Alignment ss:Horizontal="Center" ss:Vertical="Bottom"/>
  </Style>
  <Style ss:ID="s26">
   <Alignment ss:Horizontal="Right" ss:Vertical="Center"/>
   <Font ss:FontName="Abadi MT Condensed Extra Bold" x:Family="Swiss" ss:Size="9"
    ss:Color="#000000"/>
   <NumberFormat ss:Format="#,##0.00##"/>
  </Style>
  <Style ss:ID="s27">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Font ss:FontName="Abadi MT Condensed Extra Bold" x:Family="Swiss" ss:Size="9"
    ss:Color="#000000"/>
   <NumberFormat ss:Format="#,##0.00##"/>
  </Style>
  <Style ss:ID="s28">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="9" ss:Color="#000000"/>
   <NumberFormat ss:Format="#,##0.00##"/>
  </Style>
  <Style ss:ID="s32">
   <Font ss:FontName="DengXian" x:CharSet="134" ss:Size="12" ss:Color="#000000"/>
   <NumberFormat/>
  </Style>
  <Style ss:ID="s33">
   <Font ss:FontName="DengXian" x:CharSet="134" ss:Size="12" ss:Color="#000000"/>
  </Style>
  <Style ss:ID="s34">
   <Alignment ss:Horizontal="Right" ss:Vertical="Center"/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="12" ss:Color="#000000"/>
   <NumberFormat ss:Format="#,##0.00##"/>
  </Style>
  <Style ss:ID="s59">
   <Alignment ss:Horizontal="Left" ss:Vertical="Top"/>
  </Style>
  <Style ss:ID="s69">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Font ss:FontName="DengXian" x:CharSet="134" ss:Size="12" ss:Color="#000000"/>
   <NumberFormat/>
  </Style>
  <Style ss:ID="s70">
   <Alignment ss:Horizontal="Center" ss:Vertical="Bottom"/>
   <Font ss:FontName="DengXian" x:CharSet="134" ss:Size="12" ss:Color="#000000"/>
  </Style>
  <Style ss:ID="s71">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="12" ss:Color="#000000"/>
   <NumberFormat ss:Format="#,##0.00##"/>
  </Style>
  <Style ss:ID="s74">
   <Alignment ss:Horizontal="Left" ss:Vertical="Top" ss:WrapText="1"/>
  </Style>
 </Styles>
 <Worksheet ss:Name="美食销售排行">
  <Table ss:ExpandedColumnCount="${(categoryRankList?size)*2+5}" ss:ExpandedRowCount="${totalRankList?size+5}" x:FullColumns="1"
   x:FullRows="1" ss:StyleID="s33" ss:DefaultColumnWidth="65.25"
   ss:DefaultRowHeight="15.75">
   <Column ss:Index="2" ss:StyleID="s32" ss:Width="102"/>
   <Column ss:StyleID="s33" ss:AutoFitWidth="0" ss:Width="114.75"/>
   <Row>
    <Cell ss:MergeAcross="2" ss:MergeDown="1" ss:StyleID="s69"><Data ss:Type="String">总排行</Data></Cell>
    <Cell ss:MergeAcross="${(categoryRankList?size)*2-1}" ss:StyleID="s70"><Data ss:Type="String">分类排行</Data></Cell>
   </Row>
   <Row>
   	<#if categoryRankList?? && (categoryRankList?size>0)>
   		<#list categoryRankList as item>
   			<Cell  <#if (item?index=0)>ss:Index="4"</#if> ss:MergeAcross="1" ss:StyleID="s71"><Data ss:Type="String">${item.categoryName}</Data></Cell>
   		</#list>
   	</#if>
   </Row>
   <Row>
    <Cell><Data ss:Type="String">排行</Data></Cell>
    <Cell ss:StyleID="s33"><Data ss:Type="String">美食名称</Data></Cell>
    <Cell><Data ss:Type="String">销售数量</Data></Cell>
    <#if categoryRankList?? && (categoryRankList?size>0)>
   		<#list categoryRankList as item>
   			<Cell ss:StyleID="s34"><Data ss:Type="String">美食名称</Data></Cell>
    		<Cell ss:StyleID="s34"><Data ss:Type="String">数量</Data></Cell>
   		</#list>
   	</#if>
   </Row>
    <#if totalRankList?? && (totalRankList?size>0)>
   		<#list totalRankList as item>
   			<Row>
		    <Cell><Data ss:Type="Number">${item?index+1}</Data></Cell>
		    <Cell ss:StyleID="s17"><Data ss:Type="String">${item.goodsName}</Data></Cell>
		    <Cell ss:StyleID="s32"><Data ss:Type="Number">${item.saleQuantity}</Data></Cell>
		    <#if categoryRankList?? && (categoryRankList?size>0)>
   				<#list categoryRankList as category>
	   				<#if category.items?? && (category.items?size>0)>
	   					<#if (category.items?size>=(item?index+1))>
	   						<#list category.items as detail>
		   						<#if item?index = detail?index>
		   							<Cell ss:StyleID="s17"><Data ss:Type="String">${detail.goodsName}</Data></Cell>
				    				<Cell ss:StyleID="s32"><Data ss:Type="Number">${detail.saleQuantity}</Data></Cell>
		   						</#if>	
	   						</#list>
	   					<#else>
	   						<Cell ss:StyleID="s17"></Cell>
			    			<Cell ss:StyleID="s32"></Cell>
	   					</#if>
	   				<#else>
	   					<Cell ss:StyleID="s17"></Cell>
			    		<Cell ss:StyleID="s32"></Cell>
	   				</#if>
   				</#list>
		   	</#if>
		   </Row>
   		</#list>
   	</#if>
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <PageSetup>
    <Header x:Margin="0.3"/>
    <Footer x:Margin="0.3"/>
    <PageMargins x:Bottom="0.75" x:Left="0.7" x:Right="0.7" x:Top="0.75"/>
   </PageSetup>
   <Zoom>97</Zoom>
   <Selected/>
   <TopRowVisible>0</TopRowVisible>
   <Panes>
    <Pane>
     <Number>3</Number>
     <ActiveRow>1</ActiveRow>
    </Pane>
   </Panes>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
</Workbook>
