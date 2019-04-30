@echo off

:: Call java jar file to make dimens.xml files.
:: After screenMatchDP.jar,the first param is base dp.
:: And stitching the parameters that you need to fit later.
:: For example:java -jar screenMatchDP.jar 360 411 480
:: Default dps is 384 392 400 410 411 480 533 592 600 640 662 720 768 800 811 820 960 961 1024 1280 1365

:: 1——————Modify the baseline and adaptation parameters here
@java -Dfile.encoding=utf-8 -jar %~dp0\screenMatchDP.jar 1080 720 392

@rem "If you are not want to make some dimens.xml files below: "
@echo Be deleting files of ...

@rmdir /q /s .\res\values-sw384dp
::@rmdir /q /s .\res\values-sw392dp
@rmdir /q /s .\res\values-sw400dp
@rmdir /q /s .\res\values-sw410dp
@rmdir /q /s .\res\values-sw411dp
::@rmdir /q /s .\res\values-sw480dp
@rmdir /q /s .\res\values-sw533dp
@rmdir /q /s .\res\values-sw592dp
::@rmdir /q /s .\res\values-sw600dp
@rmdir /q /s .\res\values-sw640dp
@rmdir /q /s .\res\values-sw662dp
:: @rmdir /q /s .\res\values-sw720dp
@rmdir /q /s .\res\values-sw768dp
::@rmdir /q /s .\res\values-sw800dp
@rmdir /q /s .\res\values-sw811dp
@rmdir /q /s .\res\values-sw820dp
::@rmdir /q /s .\res\values-sw960dp
@rmdir /q /s .\res\values-sw961dp
::@rmdir /q /s .\res\values-sw1024dp
@rmdir /q /s .\res\values-sw1280dp
@rmdir /q /s .\res\values-sw1365dp
@echo Delete complete!

pause

