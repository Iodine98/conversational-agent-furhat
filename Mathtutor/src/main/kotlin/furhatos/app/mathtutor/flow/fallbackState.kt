package furhatos.app.mathtutor.flow

import furhatos.app.mathtutor.strings.getFallBackStateStrings
import furhatos.flow.kotlin.*


val FallBackState: State = state {
    var noinput = 0
    var nomatch = 0

    onResponse {
        emotionHandler.performGesture(furhat, "Encouraging")
        ++nomatch
        if (nomatch == 1) {
            furhat.say(furhat.getFallBackStateStrings().onResponseFirstResponse)
        } else {
            val responses = furhat.getFallBackStateStrings().onResponseOtherResponses
            furhat.say(responses.random())
        }
        emotionHandler.performGesture(furhat, "Neutral")
        reentry()
    }

    onNoResponse {
        emotionHandler.performGesture(furhat, "Encouraging")
        ++noinput
        if (noinput == 1) {
            furhat.say(furhat.getFallBackStateStrings().onNoResponseFirstResponse)
        } else {
            val responses = furhat.getFallBackStateStrings().onNoResponseOtherResponses
            furhat.say(responses.random())
        }
        emotionHandler.performGesture(furhat, "Neutral")
        reentry()
    }

    onResponseFailed {
        furhat.say("My apologies, my speech recognizer is out of order. Please try again soon.")
        terminate()
    }
}