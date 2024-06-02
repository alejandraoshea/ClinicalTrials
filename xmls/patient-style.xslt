<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" encoding="UTF-8" indent="yes" standalone="yes"/>

    <!-- Template to match Patient element -->
    <xsl:template match="/Patient">
        <html>
            <head>
                <title>Patient Information</title>
            </head>
            <body>
                <h2>Patient Details</h2>
                <p><b>Email:</b> <xsl:value-of select="@emailPatient" /></p>
                <p><b>Name:</b> <xsl:value-of select="namePatient" /></p>
                <p><b>Phone:</b> <xsl:value-of select="phonePatient" /></p>
                <p><b>Blood Type:</b> <xsl:value-of select="bloodType" /></p>
                <p><b>Disease:</b> <xsl:value-of select="disease" /></p>
                <p><b>Cured:</b> <xsl:value-of select="cured" /></p>
                <p><b>Date of Birth:</b> <xsl:value-of select="dateOfBirth" /></p>
                
                <!-- Display doctor information -->
                <h3>Doctor Details</h3>
                <p><b>Email:</b> <xsl:value-of select="doctor/@email" /></p>
                <p><b>Name:</b> <xsl:value-of select="doctor/name" /></p>
                <p><b>Phone:</b> <xsl:value-of select="doctor/phone" /></p>
                <p><b>Specialization:</b> <xsl:value-of select="doctor/specialization" /></p>
                
                <!-- Display investigational products -->
                <h3>Investigational Products</h3>
                <table border="1">
                    <tr>
                        <th>Amount</th>
                        <th>Description</th>
                        <th>Type</th>
                    </tr>
                    <xsl:for-each select="doctor/investigationalProducts/investigationalProduct">
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
