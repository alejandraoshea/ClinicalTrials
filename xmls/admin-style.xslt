<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" encoding="UTF-8" indent="yes" standalone="yes"/>

    <xsl:template match="/Administrator">
        <html>
            <head>
                <title>Administrator Information</title>
            </head>
            <body>
                <h2>Administrator Details</h2>
                <p><b>Email:</b> <xsl:value-of select="@email" /></p>
                <p><b>Name:</b> <xsl:value-of select="name" /></p>
                <p><b>Phone:</b> <xsl:value-of select="phone" /></p>
                <h3>Trials</h3>
                <table border="1">
                    <tr>
                        <th>Requirements</th>
                        <th>Total Amount Invested</th>
                    </tr>
                    <xsl:for-each select="trials/trial">
                        <tr>
                            <td><xsl:value-of select="requirements" /></td>
                            <td><xsl:value-of select="totalAmountInvested" /></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
