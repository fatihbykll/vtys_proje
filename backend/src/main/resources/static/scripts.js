document.addEventListener('DOMContentLoaded', function() {
    const courseSelectionForm = document.getElementById('courseSelectionForm');
    const coursesSelect = document.getElementById('courses');
    const messageDiv = document.getElementById('message');

    fetch('/api/courses')
        .then(response => response.json())
        .then(data => {
            data.forEach(course => {
                const option = document.createElement('option');
                option.value = course.id;
                option.textContent = course.name;
                coursesSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Kurslar yüklenirken hata oluştu:', error));

    courseSelectionForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const studentName = document.getElementById('studentName').value;
        const selectedCourses = Array.from(coursesSelect.selectedOptions).map(option => option.value);

        fetch('/api/saveCourseSelection', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ studentName, selectedCourses })
        })
        .then(response => response.json())
        .then(data => {
            messageDiv.textContent = 'Kurs seçimi başarıyla kaydedildi!';
            messageDiv.style.color = 'green';
        })
        .catch(error => {
            messageDiv.textContent = 'Kurs seçimi kaydedilirken hata oluştu.';
            messageDiv.style.color = 'red';
            console.error('Kurs seçimi kaydedilirken hata oluştu:', error);
        });
    });
});