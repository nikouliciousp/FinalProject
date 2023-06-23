setTimeout(function() {
    var successMessage = document.getElementById('successMessage');
    var errorMessage = document.getElementById('errorMessage');

    if (successMessage) {
        successMessage.style.display = 'none';
    }

    if (errorMessage) {
        errorMessage.style.display = 'none';
    }
}, 2000);

function confirmDelete1(event) {
    if (!confirm("Are you sure you want to delete?")) {
        event.preventDefault();
        return false;
    }
    return true;
}

function confirmDelete2(event) {
    if (!confirm("Are you sure you want to delete?")) {
        event.preventDefault();
        return false;
    }
    return true;
}

setTimeout(function set1() {
    var successMessage1 = document.getElementById('successMessage1');
    var errorMessage1 = document.getElementById('errorMessage1');
    var successMessage2 = document.getElementById('successMessage2');
    var errorMessage2 = document.getElementById('errorMessage2');

    if (successMessage2) {
        successMessage2.style.display = 'none';
    }

    if (errorMessage2) {
        errorMessage2.style.display = 'none';
    }

    if (successMessage1) {
        successMessage1.style.display = 'none';
    }

    if (errorMessage1) {
        errorMessage1.style.display = 'none';
    }
}, 2000);

