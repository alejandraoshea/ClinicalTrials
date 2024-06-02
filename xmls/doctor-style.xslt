<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" encoding="UTF-8" indent="yes" standalone="yes"/>

    <xsl:template match="/Doctor">
    <html>
        <head>
            <title>Doctor Information</title>
        </head>
        <body>
            <h2>Doctor Details</h2>
            <p><b>Email:</b> <xsl:value-of select="@email" /></p>
            <p><b>Name:</b> <xsl:value-of select="name" /></p>
            <p><b>Phone:</b> <xsl:value-of select="phone" /></p>
            <p><b>Specialization:</b> <xsl:value-of select="specialization" /></p>
            <h3>Investigational Products</h3>
            <table border="1">
                <tr>
                    <th>Amount</th>
                    <th>Description</th>
                    <th>Type</th>
                </tr>
                <xsl:for-each select="investigationalProducts/investigationalProduct">
                    <tr>
                        <td><xsl:value-of select="amount" /></td>
                        <td><xsl:value-of select="description" /></td>
                        <td><xsl:value-of select="type" /></td>
                    </tr>
                </xsl:for-each>
            </table>
        </body>
    </html>
	</xsl:template>


</xsl:stylesheet>
