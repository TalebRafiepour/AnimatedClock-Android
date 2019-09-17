# AnimatedClock-Android
Simple Clock View For Show Waiting Progress In Android

<img src="https://github.com/TalebRafiepour/AnimatedClock-Android/blob/master/acv-gif.gif" width="300"> 

## 1.setup 
Add the JitPack repository to your build file;\n
Add it in your root build.gradle at the end of repositories:

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
  
  -------------------------------------

Add the dependency
```
  dependencies {
	        implementation 'com.github.TalebRafiepour:AnimatedClock-Android:1.0.0'
	}
   
```
## 2.usage

```
<com.taleb.animatedclockview.AnimatedClockView android:layout_width="250dp" android:layout_height="250dp"
                                                   android:layout_gravity="center"
                                                   app:acv_clockColor="#748"
                                                   app:acv_animate="false"
                                                   android:id="@+id/animatedClockView"/>
```
