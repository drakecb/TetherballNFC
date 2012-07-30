JBars is a free(free software, MPL licensed) java barcode generation tool.

Features:

    * Supported Barcodes: CODE128, CODE93, INTERLEAVED 2 OF 5
    * Web based generation: Barcode Generator Servlet
    * PNG File generation: API and commandline barcode generation to a PNG file
    * JPG File generation: API and commandline barcode generation to a JPG file
    * Java image generation: API that generates a barcode as a java.awt.Image

Copyright 2004 by Andrés Ederra
All rights reserved.

You can download the MPl license at: http://www.mozilla.org/MPL/

Future Features:

	* Support for more barcode types

	
JBars distribution files:

- JBars.war Web application containing barcode generation servlet
- /libs/* and /bin/* commandLine and library jbars barcode support files

Misc Notes:

This program has been only tested under unix systems, it should also work under win32 and mac systems, but it isn't tested.

If you plan to deploy jbars servlet into a headless(no X Server present) server you must use a headless jvm or install a dummy X server(X Virtual Frame Buffer for instance)


Thanks goes to:

- Paulo Soares, the developer of the barcode generation code of the iText poject, that was the base to start this small project.

- Adriano Dadario for his help finding bugs, and giving some user feedback.

