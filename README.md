# Notes

An android application allowing to store notes in a local database.

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request

If you need an idea, check [the idea section](https://github.com/Ezveus/Notes#ideas)

## Build (easily) and deploy

	$ android update project -p .	# Create the local.properties with the path to the SDK
	$ ant debug						# Build a debug version of the project
	# If you only have one device wired to your computer
	$ ant installd					# Install on the default device
	# else if two or more devices are wired
	$ adb -s <DEVICE_ID> install ./bin/Notes-debug.apk

Then you just have to launch Notes on your device.

## Ideas

- Store on a distant database
- Add the possibility to notify/alarm for a note on a certain day at a certain hour
- Work on the design
- Export/Import the database to/from a JSON/XML file
- [Any issues reported](https://github.com/Ezveus/Notes/issues?state=open)
