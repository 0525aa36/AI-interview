import cv2
import numpy as np
from tensorflow.keras.models import load_model
import sys
import json

# Load the pre-trained emotion model
emotion_model = load_model('emotion_model.hdf5')

# Load Haar Cascades for face and eye detection
face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')
eye_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_eye.xml')

def analyze_video(video_path):
    cap = cv2.VideoCapture(video_path)
    if not cap.isOpened():
        print(f"Error: Could not open video {video_path}")
        return {}

    results = {
        "expression": [],
        "gaze": []
    }

    while cap.isOpened():
        ret, frame = cap.read()
        if not ret:
            break

        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        faces = face_cascade.detectMultiScale(gray, 1.3, 5)

        for (x, y, w, h) in faces:
            roi_gray = gray[y:y+h, x:x+w]

            # Emotion detection
            roi_gray_resized = cv2.resize(roi_gray, (48, 48))
            roi_gray_resized = roi_gray_resized.astype("float") / 255.0
            roi_gray_resized = np.expand_dims(roi_gray_resized, axis=0)
            roi_gray_resized = np.expand_dims(roi_gray_resized, axis=-1)

            emotion_prediction = emotion_model.predict(roi_gray_resized)
            max_index = int(np.argmax(emotion_prediction))
            emotion_label = ["Angry", "Disgust", "Fear", "Happy", "Sad", "Surprise", "Neutral"][max_index]
            results["expression"].append(emotion_label)

            # Gaze tracking
            eyes = eye_cascade.detectMultiScale(roi_gray)
            gaze_info = []
            for (ex, ey, ew, eh) in eyes:
                gaze_info.append({"x": ex, "y": ey, "width": ew, "height": eh})
            results["gaze"].append(gaze_info)

    cap.release()
    return results

def main(video_paths, result_path):
    all_results = {
        "expression": [],
        "gaze": []
    }

    for video_path in video_paths:
        video_results = analyze_video(video_path)
        all_results["expression"].extend(video_results.get("expression", []))
        all_results["gaze"].extend(video_results.get("gaze", []))

    with open(result_path, 'w') as f:
        json.dump(all_results, f)

if __name__ == "__main__":
    video_paths = sys.argv[1:-1]  # 비디오 파일 경로들
    result_path = sys.argv[-1]  # 결과를 저장할 파일 경로
    main(video_paths, result_path)
