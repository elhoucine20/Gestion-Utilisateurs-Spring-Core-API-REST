# Build a Modern Full Stack Developer Portfolio

Act as a senior frontend developer, UI/UX designer, and creative developer. Build a complete, modern, professional, and visually impressive portfolio website for me using the CV I upload as the primary source of information.

## 1. Technology Stack

Use the following technologies:
- **React** with TypeScript.
- **Tailwind CSS** for styling and responsive layouts.
- **Framer Motion (Motion for React)** for smooth animations and transitions.
- **Lucide React** for modern icons.
- Use clean, reusable, and well-structured React components.

## 2. CV and Personal Information

**IMPORTANT:** Carefully read my uploaded CV before writing any content.

- Extract my name, professional title, profile summary, education, technical skills, internships, professional experience, and projects.
- Use the actual information from my CV. Do not invent experience, projects, certifications, achievements, or technologies.
- Rewrite descriptions in professional English while preserving their original meaning.
- If my CV contains GitHub, LinkedIn, email, or other professional links, use them in the portfolio.
- Make my information easy to update in a centralized TypeScript data file.
- If a detail is missing, omit it rather than inventing it.

## 3. Design Direction

Create a premium, modern developer portfolio inspired by award-winning developer websites, Awwwards designs, and high-end SaaS landing pages.

The design should feel:
- Modern, elegant, and minimal.
- Creative and technically impressive.
- Professional enough for recruiters and hiring managers.
- Visually rich without being cluttered.
- Unique, with strong typography, generous spacing, subtle gradients, and beautiful visual hierarchy.

Use a **dark theme by default**, with deep black or dark navy backgrounds, subtle gradients, and an accent color such as electric blue, violet, or cyan.

Avoid generic templates, excessive rounded cards, unnecessary borders, and repetitive layouts.

## 4. Interactive Animated Background

This is one of the most important requirements.

Create a beautiful, interactive animated background that responds to mouse movement.

Possible effects:
- A subtle particle network with connected nodes.
- Glowing particles that react to the cursor.
- Dynamic gradient blobs that follow mouse movement smoothly.
- A subtle grid or starfield with depth and parallax.
- Soft light trails or cursor-reactive glow effects.

Requirements:
- Use smooth, performant animations.
- Make the background feel immersive without distracting from the content.
- Implement mouse interaction with React and TypeScript.
- Use requestAnimationFrame or an efficient animation technique when appropriate.
- Ensure the animation does not block clicks or text selection.
- Respect the user's prefers-reduced-motion setting.
- Reduce particle counts and animation complexity on mobile devices.
- Avoid unnecessary dependencies and heavy rendering that could slow down the website.

The background should feel alive and interactive, not like a static image.

## 5. Website Sections

### A. Navbar
- Minimal, elegant navigation.
- My name or personal logo.
- Links to About, Skills, Experience, Projects, and Contact.
- Smooth scrolling between sections.
- Animated active section indicator.
- Responsive mobile menu with smooth transitions.
- A visible button to download my CV if the actual CV file is available in the project.

### B. Hero Section
Create a striking first impression.

Include:
- My name from the uploaded CV.
- A professional title based on my actual profile.
- A short, engaging introduction based on my CV.
- Primary CTA: "View My Projects".
- Secondary CTA: "Contact Me".
- Links to my GitHub and LinkedIn when provided.
- A visual element such as an animated code-inspired composition, interactive glowing orb, or abstract 3D-style graphic.

Use Framer Motion for staggered text reveals, subtle entrance animations, and elegant hover effects.

The hero section should immediately communicate who I am and what I build.

### C. About Me
- Present my professional journey and education.
- Highlight my interests and development approach based on the CV.
- Use a clean editorial layout instead of a generic text card.
- Add subtle scroll-triggered animations.

### D. Technical Skills
Organize my actual skills into relevant categories, for example:
- Frontend Development.
- Backend Development.
- Databases.
- Tools and Platforms.

Only include technologies supported by my CV.

Display skills using tasteful interactive elements, icons where appropriate, and subtle hover animations. Avoid arbitrary proficiency percentages or skill ratings that are not documented in my CV.

### E. Experience and Education
- Present my internships, work experience, and education using accurate dates and descriptions from my CV.
- Use a modern timeline or another creative layout.
- Animate elements as they enter the viewport.
- Emphasize responsibilities, technologies used, and meaningful contributions when documented.

### F. Featured Projects
This section should be one of the strongest parts of the portfolio.

For every project available in my CV, display:
- Project name.
- Short description.
- Technologies used.
- Key features.
- GitHub repository link, if available.
- Live demo link, if available.
- A project screenshot or visual preview, if available.

Design project showcases with varied layouts, attractive visuals, subtle gradients, and sophisticated hover interactions.

Use Framer Motion for:
- Image scaling and reveal effects.
- Smooth hover transitions.
- Staggered project card entrances.
- Subtle parallax effects where appropriate.

Never invent repository URLs, live demos, or project screenshots. If an image is unavailable, create a tasteful CSS-based visual placeholder.

### G. Contact Section
Create an elegant contact section with:
- A compelling invitation to get in touch.
- My email address from the CV.
- GitHub and LinkedIn links when provided.
- A functional email link using mailto.
- A clean, accessible contact form only if a real submission mechanism is implemented.

Do not create a fake form that appears to send messages but does nothing.

### H. Footer
Include my name, professional links, a simple copyright notice, and a subtle animation.

## 6. Advanced Animations and Interactions

Use Framer Motion throughout the website, with restraint and purpose.

Implement:
- Smooth entrance animations.
- Scroll-triggered reveals using whileInView.
- Staggered animations for lists and project showcases.
- Interactive hover states for buttons and links.
- Smooth section transitions.
- Subtle parallax and cursor-reactive elements.
- Animated navigation indicators.
