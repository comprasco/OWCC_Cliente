<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    
        xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <!-- Attribute used for table border -->
    <xsl:attribute-set name="tableBorder">
          <xsl:attribute name="border">solid 0.1mm black</xsl:attribute>
    </xsl:attribute-set>
    <xsl:param name="ExpedienteNombre" select="1"/>
    <xsl:template match="/">
		<xsl:variable name='sumatoria'>0</xsl:variable>
        <fo:root>
              <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleLetter"
                      page-height="29.7cm" page-width="50.0cm" margin="1cm">
                  <fo:region-body/>
                </fo:simple-page-master>
              </fo:layout-master-set>
            <fo:page-sequence master-reference="simpleLetter">
              <fo:flow flow-name="xsl-region-body">
                 <fo:block>
						<fo:external-graphic src="snrlogo.jpg"/>
				 </fo:block>
                 <fo:block font-size="16pt" font-family="Helvetica" color="blue" font-weight="bold" space-after="5mm">
Indice Electronico <xsl:value-of select="$ExpedienteNombre"/>
                  </fo:block>
                  <fo:block font-size="8pt">
                      <fo:table table-layout="fixed" width="100%" border-collapse="separate">    
                        <fo:table-column column-width="3cm"/>
                        <fo:table-column column-width="5cm"/>
                        <fo:table-column column-width="3cm"/>
                        <fo:table-column column-width="3cm"/>
                        <fo:table-column column-width="3cm"/>
                        <fo:table-column column-width="6cm"/>
                        <fo:table-column column-width="3cm"/>
                        <fo:table-column column-width="3cm"/>
                        <fo:table-column column-width="3cm"/>
                        <fo:table-column column-width="5cm"/>
                        <fo:table-column column-width="3cm"/>
                        <fo:table-column column-width="3cm"/>
                        <fo:table-column column-width="3cm"/>
						<fo:table-column column-width="0cm"/>
						<fo:table-column column-width="0cm"/>
						<fo:table-column column-width="0cm"/>
						<fo:table-column column-width="0cm"/>
                            <fo:table-header>
                            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify">Identificador</fo:block>
                              </fo:table-cell>
                            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify">Nombre del Documento</fo:block>
                            </fo:table-cell>
                            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify">Tipo Documental</fo:block>
                            </fo:table-cell>
                            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify">Fecha de Creacion</fo:block>
                              </fo:table-cell>
                            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify">Fecha de Incorporacion</fo:block>
                            </fo:table-cell>
							<fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify">Huella</fo:block>
                            </fo:table-cell>
			                <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify">Funcion Resumen</fo:block>
                              </fo:table-cell>
                            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify">Orden Documento </fo:block>
                            </fo:table-cell>
							<fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify">Numero de Folios</fo:block>
                            </fo:table-cell>
                            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify">Total Acumulado Folios</fo:block>
                            </fo:table-cell>
                            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify">Formato</fo:block>
                            </fo:table-cell>
                            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify">Tamaño</fo:block>
                              </fo:table-cell>
                            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify">Origen</fo:block>
                            </fo:table-cell>
                            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify"></fo:block>
                            </fo:table-cell>
							<fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify"></fo:block>
                            </fo:table-cell>
                            <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify"></fo:block>
                            </fo:table-cell>
	                        <fo:table-cell xsl:use-attribute-sets="tableBorder">
                                <fo:block font-weight="bold" text-align="justify"></fo:block>
                            </fo:table-cell>
                        </fo:table-header>
                         
                        <fo:table-body>
                        	<xsl:for-each select="/folio/children/node/children/slot">
                        	 	<xsl:variable name='pos' select='position()' />	
                        	     <fo:table-row>  	
											<xsl:for-each select="/folio/children/node/children/slot[$pos]/properties/property">
											    <xsl:variable name='pos2' select='position()' />
												<xsl:variable name='identificador' select="//property[@key='xcsd:dID']/@value" />											    
												<fo:table-cell xsl:use-attribute-sets="tableBorder"> 	
													<fo:block>
														<xsl:choose>	
															<xsl:when test="@key='xcsd:dDocName'"><xsl:value-of select="$pos"/></xsl:when>
															<xsl:when test="@key='xcsd:docURL_encoded'"></xsl:when>
															<xsl:when test="@key='xcsd:dDocAuthor'"></xsl:when>
															<xsl:when test="@key='xcsd:docURL'"></xsl:when>
															<xsl:when test="@key='xcsd:dOriginalName'"></xsl:when>
															<xsl:otherwise><xsl:value-of select="@value"/></xsl:otherwise>															
														</xsl:choose>
														
														
									
													</fo:block>
												</fo:table-cell>
											</xsl:for-each> 	
								</fo:table-row> 	
							 </xsl:for-each>									  		
				        </fo:table-body>
                      </fo:table>
                  </fo:block>
              </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    <xsl:template match="properties">
    <fo:table-row>   
       	<fo:table-cell xsl:use-attribute-sets="tableBorder">
      		<fo:block>
				<xsl:for-each select="property">
					<xsl:variable name="var2_cur" select="."/>		
					<xsl:value-of select="@value"/>
				</xsl:for-each> 
			</fo:block>
		</fo:table-cell>
    </fo:table-row>
  </xsl:template>
  <xsl:template match="@*|node()" mode="result">
   <fo:table-row>   
       	<fo:table-cell xsl:use-attribute-sets="tableBorder">
      		<fo:block>
      		    <xsl:choose>
                    <xsl:when test="self::*">Element</xsl:when>
                    <xsl:when test="self::text()">Text</xsl:when>
                    <xsl:when test="self::comment()">Comment</xsl:when>
                    <xsl:when test="self::processing-instruction()">PI</xsl:when>
                    <xsl:when test="count(.|/)=1">Root</xsl:when>
                    <xsl:when test="count(.|../@*)=count(../@*)">Attribute</xsl:when>
                    <xsl:when test="count(.|../namespace::*)=count(../namespace::*)">Namespace</xsl:when>
                </xsl:choose>
            </fo:block>
		</fo:table-cell>
       	<fo:table-cell xsl:use-attribute-sets="tableBorder">
      		<fo:block>
                <xsl:value-of select="name()"/>
			</fo:block>
		</fo:table-cell>
		<fo:table-cell xsl:use-attribute-sets="tableBorder">
			<fo:block>
                <xsl:value-of select="."/>
			</fo:block>
		</fo:table-cell>
    </fo:table-row>
  </xsl:template>  
</xsl:stylesheet>