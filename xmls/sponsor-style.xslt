<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" encoding="UTF-8" indent="yes" standalone="yes"/>

    <xsl:template match="/Sponsor">
        <html>
            <head>
                <title>Sponsor Information</title>
            </head>
            <body>
                <h2>Sponsor Details</h2>
                <p><b>Email:</b> <xsl:value-of select="@email" /></p>
                <p><b>Name:</b> <xsl:value-of select="name" /></p>
                <p><b>Phone:</b> <xsl:value-of select="phone" /></p>
                <p><b>Card Number:</b> <xsl:value-of select="cardNumber" /></p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
