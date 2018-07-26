import QtQuick 2.1
import QtQuick.Controls 1.1
import QtWebKit 3.0

ApplicationWindow {
	width: 1080
	height: 1488
	visible: true
	WebView {
		url: Qt.application.arguments[1]
		anchors.fill: parent
	}
}
